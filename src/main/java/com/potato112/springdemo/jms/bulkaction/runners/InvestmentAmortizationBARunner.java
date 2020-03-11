package com.potato112.springdemo.jms.bulkaction.runners;


import com.potato112.springdemo.jms.bulkaction.dao.InvestmentDao;
import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.exception.StatusManagerException;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.StatusManager;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysDocument;
import com.potato112.springdemo.jms.bulkaction.model.investment.IntInvestmentItem;
import com.potato112.springdemo.jms.bulkaction.services.AsyncInvestmentAmortizationStatusChanger;
import com.potato112.springdemo.jms.bulkaction.services.InvestmentStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles sophisticated status change:
 *  - business logic with exceptions and messages
 *  - db operations  / transaction management, conditional rollbacks etc.
 */
@Component
public class InvestmentAmortizationBARunner extends ChangeStatusBARunner {

    private final InvestmentStatusManager investmentStatusManager; // fixme
    private final AsyncInvestmentAmortizationStatusChanger asyncInvestmentAmortizationStatusChanger;
    private final InvestmentDao investmentDao;

    public InvestmentAmortizationBARunner(InvestmentStatusManager investmentStatusManager, AsyncInvestmentAmortizationStatusChanger asyncInvestmentAmortizationStatusChanger, InvestmentDao investmentDao) {
        this.investmentStatusManager = investmentStatusManager;
        this.asyncInvestmentAmortizationStatusChanger = asyncInvestmentAmortizationStatusChanger;
        this.investmentDao = investmentDao;
    }

    public void investmentDocumentAmortizationProcess(String id, InvestmentStatus newInvestmentStatus, String loggedUser) {

        IntInvestmentItem investmentItem = investmentDao.getInvestmentById(id);   // !!
        ///investmentItem.setLoggedUser(loggedUser);
        investmentStatusManager.changeAmortizationProcessingStatus(investmentItem, newInvestmentStatus); // fails here null pointer
    }

    @Override
    public SysDocument getDocumentById(String id) {
        return investmentDao.getInvestmentById(id);
    }

    @Override
    public StatusManager getStatusManager() {
        return investmentStatusManager;
    }

    @Override
    protected AsyncStatusChanger getStatusChanger() {
        return asyncInvestmentAmortizationStatusChanger;
    }
}
