package com.potato112.springdemo.crud.jdbc;

import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.init.InvestmentChangeStatusBAInit;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInitiator;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionManager;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class represents, e.g. UI component that
 * - collects checked items on grid
 * - prepares params
 * - execute bulk action
 */
@Component
public class BulkActionExecutor {

    @Autowired
    private BulkActionManager bulkActionInitiator;

    public void executeBulkAction(){

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        BulkActionRunner runner =  new BulkActionRunner();
        executorService.execute(runner);
    }

    private class BulkActionRunner implements Runnable {

        @Override
        public void run() {
            SysStatus targetStatus = InvestmentStatus.PROCESSED;
            Set<String> documentIds = new HashSet<>();
            documentIds.add("1");
            documentIds.add("2");
            documentIds.add("3");
            String cancelationMessage = "";
            String loggedUser = "testUserFromExecutor";
            BulkActionInit bulkActionInit = new InvestmentChangeStatusBAInit(targetStatus, documentIds, cancelationMessage, loggedUser);

            bulkActionInitiator.initiateBulkAction(bulkActionInit);
        }
    }
}
