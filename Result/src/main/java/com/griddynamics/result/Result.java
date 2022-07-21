package com.griddynamics.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Result<T, E extends Throwable> {

    private final T value;

    private final E exception;

    private Result(T value, E exception) {

        this.value = value;
        this.exception = exception;
    }


    /**
     * creates successful result
     */
    public static <T, E extends Throwable> Result<T, E> ok(T value) {

        return new Result<>(value, null);
    }

    /**
     * creates error result
     */
    public static <T, E extends Throwable> Result<T, E> error(E error) {

        return new Result<>(null, error);
    }

    /**
     * creates result from operation
     */
    public static <T> Result<T, ? extends Throwable> of(Supplier<T> operation) {

        try {
            return new Result<>(operation.get(), null);

        }
        catch (Throwable throwable) {

            return error(throwable);
        }
    }

    /**
     * returns the successful value, or throws an exception in case of exception
     */
    public T unwrap() throws E {

        if (exception != null) {
            throw exception;
        }

        return value;
    }

    /**
     * applies the given function to the result, or does nothing in case of error
     */
    public <V> Result<V, E> map(Function<T, V> mapper) {

        if (exception == null) {
            return ok(mapper.apply(this.value));
        }

        return error(exception);
    }

    /**
     * applies the given function to the exception, or does nothing in case of success
     */
    public <V extends Throwable> Result<T, V> mapError(Function<E, V> mapper) {

        if (exception == null) {
            return ok(value);
        }

        return error(mapper.apply(exception));
    }

    /**
     * returns the successful value or a passed value in case of exception
     */
    public T orElse(T value) {

        return exception == null ? this.value : value;
    }

    /**
     * returns Optional with a value in case of success or an empty Optional in case of error
     */
    public Optional<T> toOptional() {

        return exception == null ? Optional.of(value) : Optional.empty();
    }

    /**
     * takes a function, which takes current type and returns a Result
     */
    public <X> Result<X, ? extends Throwable> flatMap(Function<T, Result<X, ? extends Throwable>> function) throws E {

        if (exception == null) {
            return function.apply(value);
        }
        throw exception;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result<?, ?> result = (Result<?, ?>) o;
        return Objects.equals(value, result.value) && Objects.equals(exception, result.exception);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, exception);
    }

    @Override
    public String toString() {

        return "Result{" + "value=" + value + ", exception=" + exception + '}';
    }

}
