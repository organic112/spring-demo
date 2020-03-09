package com.potato112.springdemo.jms.bulkaction.runners;

import com.potato112.springdemo.jms.bulkaction.dao.InvestmentDao;
import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.investment.IntInvestmentItem;
import com.potato112.springdemo.jms.bulkaction.model.investment.InvestmentDocument;
import com.potato112.springdemo.jms.bulkaction.model.investment.InvestmentProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


// Processing wrapper

@Component
public class InvestmentAmortizationProcessor {

    private InvestmentDao investmentDao;

    @Autowired
    private ProductProcessor productProcessor;


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String processInvestmentsAndCreateAmortizationRecords(IntInvestmentItem intInvestmentItem, InvestmentStatus newStatus) {

        InvestmentDocument document = intInvestmentItem.getInvestmentDocument();
        String investmentId = document.getId();

        // Fetch investment document from DB to get persistence context
        InvestmentDocument investmentDocument = investmentDao.getInvestmentDocumentById(investmentId);

        // some business logic, create objects etc.
        InvestmentProduct investmentProduct = new InvestmentProduct();

        productProcessor.processProduct(investmentProduct, newStatus);

        return "FIXME processing message";
    }
}