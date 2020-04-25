package com.potato112.springdemo.jms.crud;

import org.hibernate.FlushMode;

/**
 * Create and read operations for DAO services.
 */
public interface CreateReadService<E> extends ReadService<E> {

    E create(E e);

    void createNoFlush(E e);

    void flush();

    void setFlushMode(FlushMode flushMode);
}
