package com.sthn.messaging.validator;

public interface IValidator<T, V> {

    public V validate(T t);
}
