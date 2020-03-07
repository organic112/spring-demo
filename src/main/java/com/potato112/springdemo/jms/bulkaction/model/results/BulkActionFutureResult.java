package com.potato112.springdemo.jms.bulkaction.model.results;

import java.io.Serializable;

public class BulkActionFutureResult implements Serializable {

    private String processedObjectCode;
    private boolean success = true;
    private String resultDetails;
    private Exception exception;

    public BulkActionFutureResult() {
    }

    public static BulkActionFutureResult makeSuccess(final String processedObjectCode, final String resultDetails) {

        BulkActionFutureResult result = new BulkActionFutureResult();
        result.processedObjectCode = processedObjectCode;
        result.success = true;
        result.resultDetails = resultDetails;
        return result;
    }

    public static BulkActionFutureResult makeFailure(final String processedObjectCode, final String resultDetails, final Exception exception) {

        BulkActionFutureResult result = new BulkActionFutureResult();
        result.processedObjectCode = processedObjectCode;
        result.success = false;
        result.resultDetails = resultDetails;
        result.exception = exception;
        return result;
    }

    private static void makeResult(boolean isSuccess){
        // TODO refactor
    }

    public String getProcessedObjectCode() {
        return processedObjectCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public Exception getException() {
        return exception;
    }
}
