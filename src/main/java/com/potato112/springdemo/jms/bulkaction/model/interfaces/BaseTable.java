package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import java.time.LocalDateTime;

public interface BaseTable  {


    String getCode();

    String getCreateUser();

    LocalDateTime getCreateDate();

    String getUpdateUser();

    void setUpdateUser(String loggedUser);

    LocalDateTime getUpdateDate();
}
