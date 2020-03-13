package com.potato112.springdemo.jms.crud;


import com.potato112.springdemo.jms.doclock.EntityDeleteException;

/**
 * Interface for all DAO services to perform CRUD operations
 */
public interface CRUDService<E> extends CreateReadService<E> {

    E update(E e);

    void delete(E e);

    void tryDelete(E e) throws EntityDeleteException;

    int updateWithNameQuery(String namedQueryName);
}
