package com.potato112.springdemo.jms.bulkaction.runners;


import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentProductStatus;
import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.investment.InvestmentProduct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Runs inside InvestmentAmortizationProcessor
 * - handles transactions
 * - returns processing messages
 * - handles status change of Product
 */

@Component
public class ProductProcessor {



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processProduct(InvestmentProduct investmentProduct, InvestmentStatus newStatus) {

        // Product messages first created in db

        // convert Investement target status to product status
        // some logic with exceptions (processing some collections based on product)

        // exceptions handling
        // in exceptions handling settning product status
        investmentProduct.setInvestmentProductStatus(InvestmentProductStatus.PROCESSED);
    }
}
