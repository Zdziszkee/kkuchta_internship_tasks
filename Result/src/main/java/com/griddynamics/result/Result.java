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



    public static <T, E extends Throwable> Result<T, ? extends E> ok(T value) {

        return new Result<>(value, null);
    }

    public static <T, E extends Throwable> Result<T, ? extends Throwable> error(E error) {

        return new Result<>(null, error);
    }

    public static <T> Result<T, ? extends Throwable> of(Supplier<T> operation) {

        try {
            return new Result<>(operation.get(), null);

        }
        catch (Throwable throwable) {

            return error(throwable);
        }
    }

    public T unwrap() throws E {

        if (exception != null) {
            throw exception;
        }

        return value;
    }

    public <V> Result<V, ? extends Throwable> map(Function<T, V> mapper) {

        if (exception == null) {
            return ok(mapper.apply(this.value));
        }

        return error(exception);

    }

    public <V extends Throwable> Result<?, ? extends Throwable> mapError(Function<E, V> mapper) {


        if (exception == null) {
            return ok(value);
        }

        return error(mapper.apply(exception));
    }

    public T orElse(T value) {

        return exception == null ? this.value : value;
    }

    public Optional<T> toOptional() {

        return exception == null ? Optional.of(value) : Optional.empty();
    }

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
