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

}
