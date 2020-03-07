package com.potato112.springdemo.jms.bulkaction.services;

import com.potato112.springdemo.jms.simple.BaseMDC;
import com.potato112.springdemo.jms.bulkaction.model.interfaces.BulkActionInit;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
public class AbstractBulkActionMDC extends BaseMDC {

    private static String JMS_RESULT_ID = "RESULT_ID";


    @Override
    public void processMessage(ObjectMessage message, String userName) {

        // get bulk action id
        String id = getResultId(message);

        // get init object
        BulkActionInit bulkActionInit = getBulkAction(message, id);
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
