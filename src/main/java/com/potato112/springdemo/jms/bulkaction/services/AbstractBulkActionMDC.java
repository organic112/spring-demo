package com.potato112.springdemo.jms.bulkaction.services;

import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionType;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionResultManager;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import com.potato112.springdemo.jms.simple.BaseMDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public abstract class AbstractBulkActionMDC<INIT extends BulkActionInit> extends BaseMDC {

    private static final String JMS_RESULT_ID = "RESULT_ID";

    @Autowired
    private BulkActionResultManager bulkActionResultManager;

    protected abstract BulkActionsRunResult runBulkAction(INIT bulkActionInit);


    @Override
    public void onMessage(Message message) {
        System.out.println("received message in abstract..");
        super.onMessage(message);
    }

    @Override
    public void processMessage(ObjectMessage message, String userName) {

        System.out.println("received message in abstract..");

        // get bulk action id
        final String id = getResultId(message);

        // get init object
        final BulkActionInit bulkActionInit = getBulkAction(message, id);

        bulkActionResultManager.markInProgress(id);

        final BulkActionsRunResult result = runBulkActionWrapper(bulkActionInit);
        bulkActionResultManager.completeBulkAction(id, result);

        final BulkActionType type = bulkActionInit.getType();
        // send notification about BA complete
    }

    BulkActionsRunResult runBulkActionWrapper(final BulkActionInit bulkActionInit) {

        try {

            INIT init = (INIT) bulkActionInit;
            return runBulkAction(init);

        } catch (Exception e) {

            System.out.println("Failed to run BulkAction in MDC" + e.getMessage());

            return new BulkActionsRunResult(false, e.getLocalizedMessage());
        }
    }


    private String getResultId(ObjectMessage objectMessage) {

        String resultID = getStringPropertyFromMessage(objectMessage, JMS_RESULT_ID);

        if (null == resultID || "".equals(resultID)) {
            StringBuilder errorMessage = new StringBuilder("JMS Message has no valid Id.");
            errorMessage.append("Invalid property: ");
            errorMessage.append(JMS_RESULT_ID);

            throw new IllegalArgumentException(errorMessage.toString());
        }
        return resultID;
    }


    private BulkActionInit getBulkAction(final ObjectMessage objectMessage, final String id) {

        Object object;
        try {
            object = objectMessage.getObject();

        } catch (JMSException e) {
            System.out.println("Failed to get object from message" + e.getMessage());

            // TODO handle this situation with dedicated bulkActionManager
            throw new IllegalStateException("Internal JMS ERROR");
        }
        validateJMSMessageObject(object);

        return (BulkActionInit) object;
    }

    private void validateJMSMessageObject(Object object) {

        if (null == object) {
            throw new IllegalArgumentException("Bulk action failed. JMS ObjectMessage should contain object");
        }
        if (!(object instanceof BulkActionInit)) {
            throw new IllegalArgumentException("Bulk action failed. JMS ObjectMessage doesn't contain bulk action");
        }
    }


}
