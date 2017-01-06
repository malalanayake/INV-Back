package com.sthn.service;


public interface GenericCRUDService<T> {

    T create(T object) throws Exception;

    T get(long id);

    T update(T object);

    T delete(T object);

}
