package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionResult;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionStatus;

public interface BulkActionManager {


    BulkActionResult getBulkActionResultById(String Id);

    void markInProgress(String Id);

    void finishBulkAction(String bulkActionResultId, BulkActionsRunResult runResult);

    void finishBulkAction(String bulkActionResultId, BulkActionStatus bulkActionStatus, String bulkActionMessage);

}
