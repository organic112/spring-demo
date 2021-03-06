package com.potato112.springdemo.jms.bulkaction.model.results;

import java.util.ArrayList;
import java.util.List;

public class BulkActionsRunResultVo {

    private Boolean success = null;
    private String details;
    private List<BulkActionFutureResultVo> resultList = new ArrayList<>();


    public BulkActionsRunResultVo() {
    }

    public BulkActionsRunResultVo(Boolean success) {
        validateResult(success);
        this.success = success;
    }

    public BulkActionsRunResultVo(Boolean success, String details) {
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

    public List<BulkActionFutureResultVo> getResultList() {
        return resultList;
    }

    public void setResultList(List<BulkActionFutureResultVo> resultList) {
        this.resultList = resultList;
    }

    private void validateResult(Boolean success) {

        if (null == success) {
            throw new IllegalArgumentException("Result should be success true or false, not null");
        }
    }
}
