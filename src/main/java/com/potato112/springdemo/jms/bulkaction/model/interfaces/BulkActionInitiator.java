package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import java.util.Queue;

/**
 * provides methods to initiate bulk action
 */
public interface BulkActionInitiator {

    /**
     * Starts BA processing
     * - first validates and may throw exception
     * - stores db info about BA initiation
     * - sends BA message to JMS queue
     * Bulk Action is started when message is received by MDC Message Driven Component (receiver)
     */
    void initiateBulkAction(BulkActionInit bulkActionInit);


    void initiateBulkAction(final BulkActionInit bulkActionInit, String customDestination);
}
