package com.potato112.springdemo.jms.bulkaction.runners;

import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionFutureResult;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.Set;
import java.util.concurrent.Future;

public abstract class AbstractBARunner {

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

            System.out.println(e.getMessage());
            BulkActionFutureResult failResult = BulkActionFutureResult.makeFailure(objectId, e.getLocalizedMessage(), e);
            return failResult;
        }
    }

    protected void validateInit(BulkActionInit bulkActionInit) {

        if (null == bulkActionInit) {
            throw new IllegalArgumentException("BulkActionInit cannot be null");
        }

        Set<String> documentIdSet = bulkActionInit.getAffectedDocumentIds();
        if (null != documentIdSet && documentIdSet.isEmpty()) {
            throw new IllegalArgumentException("BulkActionInit Document Id list can't be null or empty");
        }
    }


}
