package com.potato112.springdemo.jms.bulkaction.runners;

import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionFutureResult;
import com.potato112.springdemo.jms.bulkaction.model.init.ChangeStatusBAInit;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.bulkaction.model.init.ChangeStatusParams;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.StatusManager;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysDocument;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.SysStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public abstract class ChangeStatusBARunner<OBJTYPE extends SysDocument, STATUS extends SysStatus> extends AbstractBARunner {

    public abstract OBJTYPE getDocumentById(String id);

    public abstract StatusManager<OBJTYPE, STATUS> getStatusManager();

    protected abstract AsyncStatusChanger<OBJTYPE, STATUS> getStatusChanger();

    public BulkActionsRunResult run(final BulkActionInit bulkActionInit) {

        @SuppressWarnings("unchecked")
        ChangeStatusBAInit<OBJTYPE, STATUS> init = (ChangeStatusBAInit<OBJTYPE, STATUS>) bulkActionInit;

        boolean totalSuccess = true;
        Future<BulkActionFutureResult> bulkActionResult;
        final Map<String, Future<BulkActionFutureResult>> bulkActionResults;
        bulkActionResults = new HashMap<>();

        final BulkActionsRunResult result = new BulkActionsRunResult();

        validateInit(bulkActionInit);

        final Set<String> ids = init.getDocumentIds();

        //starts threads
        for (final String id : ids) {
            bulkActionResult = changeStatus(id, bulkActionInit);
            bulkActionResults.put(id, bulkActionResult);
        }

        // check results
        String details = generateDetailsMessage(init);

        for (String id : bulkActionResults.keySet()) {

            Future<BulkActionFutureResult> future = bulkActionResults.get(id);
            BulkActionFutureResult singleProcessingResult = getSingleProcessingResult(id, future);
            totalSuccess = totalSuccess && singleProcessingResult.isSuccess();
            result.getResultList().add(singleProcessingResult);
        }

        result.setSuccess(totalSuccess);
        result.setDetails(details);
        return result;
    }

    /**
     * Wrapper for changing status of single sys document.
     * By default method calls AsyncStatusChanger, that runs in separate thread and separate transaction
     */
    protected Future<BulkActionFutureResult> changeStatus(String id, BulkActionInit bulkActionInit) {
        AsyncStatusChanger<OBJTYPE, STATUS> statusChanger = getStatusChanger();
        return statusChanger.processSingleItemAsync(id, castInit(bulkActionInit), this);
    }



    protected void changeStatusOfSingleDocument(final OBJTYPE document, final ChangeStatusBAInit<OBJTYPE, STATUS> bulkActionInit) {

        final String loggedUser = bulkActionInit.getLoggedUser();
        final ChangeStatusBAInit<OBJTYPE, STATUS> init = castInit(bulkActionInit);
        final STATUS targetStatus = init.getTargetStatus();
        final Set<String> documentIdSet = init.getDocumentIds();
        final String cancelMessage = init.getCancellationMessage();

        validateUserPermissionsToDocument(loggedUser, document);
        validateSingleDocumentIdAgainstOtherIds(document, documentIdSet, targetStatus);

        ChangeStatusParams<OBJTYPE, STATUS> params = new ChangeStatusParams<>();
        params.setDocument(document);
        params.setNewStatus(targetStatus);
        params.setCancellationMessage(cancelMessage);
        params.setLoggedUser(loggedUser);

        getStatusManager().changeStatus(params);
    }

    protected void validateUserPermissionsToDocument(String loggedUser, OBJTYPE document) {
        // TODO implement if need this check on backend side
    }

    protected void validateSingleDocumentIdAgainstOtherIds(OBJTYPE document, Set<String> allSelectedIds, STATUS targetStatus) {
        // TODO implement if need this check on backend side
    }

    @Override
    protected void validateInit(BulkActionInit bulkActionInit) {
        super.validateInit(bulkActionInit);
        if (!(bulkActionInit instanceof ChangeStatusBAInit<?, ?>)) {
            throw new IllegalArgumentException("Bulk action init should be of type ChangeStatusBulkAction");
        }
    }

    @Override
    protected String generateDetailsMessage(BulkActionInit bulkActionInit) {

        ChangeStatusBAInit<OBJTYPE, STATUS> init = castInit(bulkActionInit);
        STATUS targetStatus = init.getTargetStatus();
        String message = "Status change to: ";
        return message.concat(targetStatus.toString());
    }
    @SuppressWarnings("unchecked")
    private ChangeStatusBAInit<OBJTYPE, STATUS> castInit(final BulkActionInit bulkActionInit) {
        return (ChangeStatusBAInit<OBJTYPE, STATUS>) bulkActionInit;
    }


}
