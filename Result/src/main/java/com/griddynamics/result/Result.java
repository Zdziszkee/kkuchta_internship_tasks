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


    public static <T, E extends Throwable> Result<T, E> ok(T value) {


        return new Result<>(value, null);
    }

    public static <T, E extends Throwable> Result<T, E> error(E error) {


        return new Result<>(null, error);
    }

    public static <T, E extends Throwable> Result<T, E> of(Supplier<T> operation) {

        try {
            return new Result<>(operation.get(), null);

        }
        catch (Throwable throwable) {

            return Result.error((E) throwable);
        }
    }

    public T unwrap() throws E {

        if (exception != null) {
            throw exception;
        }

        return value;
    }

    public <V> Result<V, E> map(Function<T, V> mapper) {

        try {

            return ok(mapper.apply(this.value));
        }
        catch (Throwable throwable) {
            return error(exception);
        }
    }

    public <V extends Throwable> Result<T, V> mapError(Function<E, V> mapper) {

        try {

            return ok(value);
        }
        catch (Throwable throwable) {
            return error(mapper.apply(exception));
        }
    }

    public T orElse(T value) {

        return exception == null ? this.value : value;
    }

    public Optional<T> toOptional() {

        return exception == null ? Optional.of(value) : Optional.empty();
    }

    public <X, Y extends Throwable> Result<X, Y> flatMap(Function<T, Result<X, Y>> function) throws E {

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
