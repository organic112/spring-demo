package com.potato112.springdemo.crud.jdbc.model;

import java.io.Serializable;

public abstract class BaseVO implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
