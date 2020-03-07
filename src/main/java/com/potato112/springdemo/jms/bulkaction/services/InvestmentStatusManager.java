package com.potato112.springdemo.jms.bulkaction.services;

import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.exception.AlreadyLockedException;
import com.potato112.springdemo.jms.bulkaction.model.exception.StatusManagerException;
import com.potato112.springdemo.jms.bulkaction.model.init.ChangeStatusParams;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.StatusManager;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysStatus;
import com.potato112.springdemo.jms.bulkaction.model.investment.IntInvestmentItem;
import com.potato112.springdemo.jms.bulkaction.runners.InvestmentAmortizationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InvestmentStatusManager implements StatusManager<IntInvestmentItem, InvestmentStatus> {

    @Autowired
    private InvestmentAmortizationProcessor investmentAmortizationProcessor;


    @Override
    public boolean canChangeStatus(IntInvestmentItem document, InvestmentStatus newStatus) {
        return isLegalStatusChange(document, newStatus);
    }

    @Override
    public boolean canChangeStatus(IntInvestmentItem document, InvestmentStatus newStatus, String cancelationMessage) {

        return isLegalStatusChange(document, newStatus);
    }

    @Override
    public void changeStatus(ChangeStatusParams<IntInvestmentItem, InvestmentStatus> params) throws AlreadyLockedException {


        IntInvestmentItem investmentItem = params.getDocument();

        InvestmentStatus newStatus = params.getNewStatus();
        String loggedUser = params.getLoggedUser();

        canChangeStatus(investmentItem, newStatus);

        changeStatus(investmentItem, newStatus, loggedUser);
    }

    private void changeStatus(IntInvestmentItem intInvestmentItem, InvestmentStatus newStatus, String loggedUser) {

        // TODO
        // lock item
        // set new status
        //intInvestmentItem.setInvestmentStatus(InvestmentStatus.PROCESSED);
        // update item in dB
        // remove item lock

        validateProcessingResult("FIXME processing message", intInvestmentItem);
        System.out.println("STATUS MANAGER: DOCUMENT PROCESSED - STATUS CHANGED: old: " + intInvestmentItem.getInvestmentStatus()
                + " new:" + newStatus);

    }


    private boolean isLegalStatusChange(IntInvestmentItem intInvestmentItem, InvestmentStatus newStatus) {
        InvestmentStatus actualStatus = intInvestmentItem.getInvestmentStatus();
        Set<SysStatus> exitStatuses = actualStatus.getExitStatuses();

        try {
            if (!exitStatuses.isEmpty() && exitStatuses.contains(newStatus)) {
                return exitStatuses.contains(newStatus);
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }


    public void changeAmortizationProcessingStatus(IntInvestmentItem intInvestmentItem, InvestmentStatus newStatus) {

        String processingMessage = investmentAmortizationProcessor.processInvestmentsAndCreateAmortizationRecords(intInvestmentItem, newStatus);

        validateProcessingResult(processingMessage, intInvestmentItem);
    }

    private void validateProcessingResult(String message, IntInvestmentItem intInvestmentItem) {

        if (!intInvestmentItem.getInvestmentStatus().equals(InvestmentStatus.PROCESSED)) {
            throw new StatusManagerException(message);
        }
    }
}
