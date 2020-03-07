package com.potato112.springdemo.jms.bulkaction.model.results;

import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionFutureResult;

import java.util.ArrayList;
import java.util.List;

public class BulkActionsRunResult {


    private Boolean success = null;
    private String details;
    private List<BulkActionFutureResult> resultList = new ArrayList<>();


    public BulkActionsRunResult() {
    }

    public BulkActionsRunResult(Boolean success) {
        validateResult(success);
        this.success = success;
    }

    public BulkActionsRunResult(Boolean success, String details) {
        this(success);
        this.details = details;
    }

    public Boolean getSuccess() {
        validateResult(success);
        return success;
    }

    public void setSuccess(Boolean success) {
        validateResult(success);
        this.success = success;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<BulkActionFutureResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<BulkActionFutureResult> resultList) {
        this.resultList = resultList;
    }

    private void validateResult(Boolean success) {

        if (null == success) {
            throw new IllegalArgumentException("Result should be success true or false, not null");
        }
    }
}
