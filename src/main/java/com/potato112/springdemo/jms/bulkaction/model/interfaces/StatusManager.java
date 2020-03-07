package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import com.potato112.springdemo.jms.bulkaction.model.init.ChangeStatusParams;
import com.potato112.springdemo.jms.bulkaction.model.exception.AlreadyLockedException;


public interface StatusManager<DOCTYPE extends SysDocument, STATUSTYPE extends SysStatus>  {

    boolean canChangeStatus(DOCTYPE document, STATUSTYPE newStatus);

    boolean canChangeStatus(DOCTYPE document, STATUSTYPE newStatus, String cancelationMessage);

    void changeStatus(ChangeStatusParams<DOCTYPE, STATUSTYPE> params) throws AlreadyLockedException;



}
