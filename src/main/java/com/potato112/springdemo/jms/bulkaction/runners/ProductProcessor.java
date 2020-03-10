package com.potato112.springdemo.jms.bulkaction.runners;


import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentProductStatus;
import com.potato112.springdemo.jms.bulkaction.model.enums.InvestmentStatus;
import com.potato112.springdemo.jms.bulkaction.model.investment.InvestmentProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProcessor.class);

    // TODO IMPLEMENT EMBEDED PROCESSING LOGIC HERE

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processProduct(InvestmentProduct investmentProduct, InvestmentStatus newStatus) {

        LOGGER.info("Start of embed processing in new transaction... Target status:" + newStatus.name());

        // Product messages first created in db

        // convert Investement target status to product status
        // some logic with exceptions (processing some collections based on product)

        // exceptions handling
        // in exceptions handling settning product status
        investmentProduct.setInvestmentProductStatus(InvestmentProductStatus.PROCESSED);
    }
}
