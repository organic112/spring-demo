package com.potato112.springdemo.jms.bulkaction.services;


import com.potato112.springdemo.jms.bulkaction.model.init.InvestmentChangeStatusBAInit;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.bulkaction.runners.InvestmentAmortizationBARunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class InvestmentChangeStatusMDC extends AbstractBulkActionMDC<InvestmentChangeStatusBAInit> {

    private static final String DESTINATION_NAME = "investmentChangeStatusBulkAction";
    private static final String FACTORY_BEAN_NAME = "customFactory";

    @Autowired
    private InvestmentAmortizationBARunner investmentAmortizationBARunner;

    //@Autowired FIXME add alternative runner
    //private InvestmentChangeStatusRunner investmentChangeStatusRunner;

    @JmsListener(destination = DESTINATION_NAME, containerFactory = FACTORY_BEAN_NAME)
    @Override
    public void onMessage(Message message) {
        super.onMessage(message);
    }

    @Override
    protected BulkActionsRunResult runBulkAction(InvestmentChangeStatusBAInit bulkActionInit) {

        return investmentAmortizationBARunner.run(bulkActionInit);
    }
}
