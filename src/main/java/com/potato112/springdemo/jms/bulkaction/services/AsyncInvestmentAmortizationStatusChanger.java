package com.potato112.springdemo.jms.bulkaction.services;

import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionFutureResult;
import com.potato112.springdemo.jms.bulkaction.model.init.ChangeStatusBAInit;
import com.potato112.springdemo.jms.bulkaction.model.investment.*;
import com.potato112.springdemo.jms.bulkaction.runners.AbstractBARunner;
import com.potato112.springdemo.jms.bulkaction.runners.AsyncStatusChanger;
import com.potato112.springdemo.jms.bulkaction.runners.InvestmentAmortizationBARunner;
import com.potato112.springdemo.jms.bulkaction.runners.ChangeStatusBARunner;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysDocument;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * Current implementation of 'business logic - Investment amortization' that ends with changed status
 * - transaction is not supported here, but embeded service handles transactions
 */
@Component
public class AsyncInvestmentAmortizationStatusChanger extends AsyncStatusChanger {


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    protected void processSingleDocument(SysDocument document, ChangeStatusBAInit init, ChangeStatusBARunner runner) {

        InvestmentAmortizationBARunner amortizationBARunner = (InvestmentAmortizationBARunner) runner;

        InvestmentStatus newStatus = (InvestmentStatus) init.getTargetStatus();
        String loggedUser = init.getLoggedUser();
        InvestmentDocument sysDocument = (InvestmentDocument) document;
        String documentId = sysDocument.getDocumentId();

        amortizationBARunner.investmentDocumentAmortizationProcess(documentId, newStatus, loggedUser);
    }

    @Override
    public Future<BulkActionFutureResult> processSingleItemAsync(String id, BulkActionInit bulkActionInit, AbstractBARunner parentRunner) {
        return super.processSingleItemAsync(id, bulkActionInit, parentRunner);
    }
}
