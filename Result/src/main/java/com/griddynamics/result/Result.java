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
     *
     * @param value - input
     * @return Result for given input
     * @param <T> input type
     * @param <E> exception type
     */
    public static <T, E extends Throwable> Result<T, ? extends E> ok(T value) {

        return new Result<>(value, null);
    }

    /**
     *
     * @param error - input error
     * @return Result for given error
     * @param <T> result value type
     * @param <E> exception value type
     */
    public static <T, E extends Throwable> Result<T, ? extends Throwable> error(E error) {

        return new Result<>(null, error);
    }

    /**
     *
     * @param operation supplier returning some value
     * @return result for that operation
     * @param <T> - optional value returned from operation
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
     *
     * @return value of that result
     * @throws E if exception occurred while getting value
     */
    public T unwrap() throws E {

        if (exception != null) {
            throw exception;
        }

        return value;
    }

    /**
     *
     * @param mapper used to transform the current result based on value inside, does nothing when there is no value
     * @return new Result
     * @param <V> type of new value
     */
    public <V> Result<V, ? extends Throwable> map(Function<T, V> mapper) {

        if (exception == null) {
            return ok(mapper.apply(this.value));
        }

        return error(exception);

    }

    /**
     *
     * @param mapper used to transform the current result based on error inside, does nothing when there is no error
     * @return new Result
     * @param <V> type of exception
     */
    public <V extends Throwable> Result<?, ? extends Throwable> mapError(Function<E, V> mapper) {


        if (exception == null) {
            return ok(value);
        }

        return error(mapper.apply(exception));
    }

    /**
     *
     * @param value returned if exception occurs
     * @return value
     */
    public T orElse(T value) {

        return exception == null ? this.value : value;
    }

    /**
     *
     * @return value as optional
     */
    public Optional<T> toOptional() {

        return exception == null ? Optional.of(value) : Optional.empty();
    }

    /**
     *
     * @param function used to transform current value to other result
     * @return new result
     * @param <X> value of new result
     * @throws E exception thrown if there is no value in current result
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
