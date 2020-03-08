package com.potato112.springdemo.jms.bulkaction.model.results;

import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionStatus;
import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// TODO as entity
public class BulkActionResult {

    private String Id;
    private BulkActionType bulkActionType;
    private BulkActionStatus bulkActionStatus;
    private LocalDateTime startProcessingDateTime;
    private LocalDateTime endProcessingDateTime;
    private String processingDetails;
    private Boolean isDeleted;

    private List<BulkActionResultMessage> resultMessages = new ArrayList<>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public BulkActionType getBulkActionType() {
        return bulkActionType;
    }

    public void setBulkActionType(BulkActionType bulkActionType) {
        this.bulkActionType = bulkActionType;
    }

    public BulkActionStatus getBulkActionStatus() {
        return bulkActionStatus;
    }

    public void setBulkActionStatus(BulkActionStatus bulkActionStatus) {
        this.bulkActionStatus = bulkActionStatus;
    }

    public LocalDateTime getStartProcessingDateTime() {
        return startProcessingDateTime;
    }

    public void setStartProcessingDateTime(LocalDateTime startProcessingDateTime) {
        this.startProcessingDateTime = startProcessingDateTime;
    }

    public LocalDateTime getEndProcessingDateTime() {
        return endProcessingDateTime;
    }

    public void setEndProcessingDateTime(LocalDateTime endProcessingDateTime) {
        this.endProcessingDateTime = endProcessingDateTime;
    }

    public String getProcessingDetails() {
        return processingDetails;
    }

    public void setProcessingDetails(String processingDetails) {
        this.processingDetails = processingDetails;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<BulkActionResultMessage> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<BulkActionResultMessage> resultMessages) {
        this.resultMessages = resultMessages;
    }
}
