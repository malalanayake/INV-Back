package com.sthn.messaging.validator;

public interface IValidator<T> {

    public T validate(T t);
}
