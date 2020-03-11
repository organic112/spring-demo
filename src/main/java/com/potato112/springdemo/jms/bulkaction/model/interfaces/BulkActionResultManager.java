package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionResult;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionStatus;

/**
 * methods to operate on bulk action results
 * covers complete bulk action live-cycle
 */
public interface BulkActionResultManager {

    /**
     * provides single bulk action result from db
     */
    BulkActionResult getBulkActionResultById(String id);

    void markInProgress(String id);

    /**
     * marks as completed
     * sets proper status
     * sets proper message based on result
     */

    void completeBulkAction(String bulkActionResultId, BulkActionsRunResult bulkActionsRunResult );

    void completeBulkAction(String bulkActionResultId, BulkActionStatus bulkActionStatus, String bulkActionMessage);

}
