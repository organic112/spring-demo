package com.potato112.springdemo.jms.bulkaction.runners;

import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionFutureResult;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.Set;
import java.util.concurrent.Future;

public abstract class AbstractBARunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBARunner.class);

    /**
     * returns Bulk Action result
     */
    public abstract BulkActionsRunResult run(final BulkActionInit bulkActionInit);

    protected abstract String generateDetailsMessage(BulkActionInit bulkActionInit);

    /**
     * returns Bulk Action failure
     */
    protected Future<BulkActionFutureResult> failure(final String code, final String message, final Exception e) {

        BulkActionFutureResult result = BulkActionFutureResult.makeFailure(code, message, e);
        return new AsyncResult<>(result);
    }

    /**
     * Processing result of single action
     */
    protected BulkActionFutureResult getSingleProcessingResult(String objectId, final Future<BulkActionFutureResult> future) {

        try {
            BulkActionFutureResult futureResult = future.get();
            return futureResult;

        } catch (Exception e) {

            LOGGER.debug("Failed to get BA future result." + e.getMessage());

            BulkActionFutureResult failResult = BulkActionFutureResult.makeFailure(objectId, e.getLocalizedMessage(), e);
            return failResult;
        }
    }

    protected void validateInit(BulkActionInit bulkActionInit) {

        LOGGER.info("Validate bulk action init start...");

        if (null == bulkActionInit) {

            throw new IllegalArgumentException("BulkActionInit cannot be null");
        }

        Set<String> documentIdSet = bulkActionInit.getDocumentIds();

        if (null != documentIdSet && documentIdSet.isEmpty()) {

            throw new IllegalArgumentException("BulkActionInit Document Id list can't be null or empty");
        }
    }
}
