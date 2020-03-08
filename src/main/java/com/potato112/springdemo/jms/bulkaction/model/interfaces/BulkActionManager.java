package com.potato112.springdemo.jms.bulkaction.model.interfaces;

import com.potato112.springdemo.jms.bulkaction.model.JMSMessageVO;
import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionStatus;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionResult;
import com.potato112.springdemo.jms.bulkaction.model.results.BulkActionsRunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.time.LocalDateTime;

@Component
public class BulkActionManager implements BulkActionInitiator, BulkActionResultManager {

    // @Autowire
    // BulkActionDAO
    // UserManager
    private static final String DESTINATION_NAME = "investmentChangeStatusBulkAction";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void initiateBulkAction(BulkActionInit bulkActionInit) {
        initiateBulkAction(bulkActionInit, DESTINATION_NAME);
    }


    @Override
    public void initiateBulkAction(BulkActionInit bulkActionInit, String customDestination) {
        validateBAInit(bulkActionInit);

        String id = createBulkActionResultInDatabase(bulkActionInit);

        if (null == id) {
            throw new IllegalStateException("bulk action result Id should not be null");
        }
        try {
            sendBulkActionToJMS(bulkActionInit, id, customDestination);
        } catch (JMSException e) {
            completeBulkAction(id, BulkActionStatus.FINISHED_ERROR, e.getLocalizedMessage());
        }
    }

    private void sendBulkActionToJMS(BulkActionInit bulkActionInit, String id, String customDestination) throws JMSException {


        System.out.println("Sending BA message to JMS...");

        String type = bulkActionInit.getType().name();
        String initUser = bulkActionInit.getLoggedUser();

        JMSMessageVO jmsMessageVO = new JMSMessageVO();
        jmsMessageVO.setId(id);
        jmsMessageVO.setBulkActionInit(bulkActionInit);

        jmsTemplate.convertAndSend(customDestination, jmsMessageVO);

        try {
            jmsTemplate.send(customDestination, session -> {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setStringProperty("RESULT_ID", id);
                objectMessage.setStringProperty("BULK_ACTION_TYPE", type);
                objectMessage.setStringProperty("userName", initUser);
                objectMessage.setObject(bulkActionInit);

                System.out.println("object id:" + objectMessage.getStringProperty("RESULT_ID"));
                return objectMessage;
            });
        } catch (Exception e) {
            throw new JMSException(e.getLocalizedMessage());
        }


    }

    private String createBulkActionResultInDatabase(BulkActionInit bulkActionInit) {
        // Implement DAO and FIXME
        return "fixmeId";
    }

    private void validateBAInit(final BulkActionInit bulkActionInit) {
        if (null == bulkActionInit) {
            throw new IllegalArgumentException("BulkActionInit should not be null.");
        }
        if (null == bulkActionInit.getType()) {
            throw new IllegalArgumentException("BulkActionInit type should not be null.");
        }
    }


    @Override
    public BulkActionResult getBulkActionResultById(String id) {

        // get Bulk action by id BulkActionResult bar = dao.getBulkActionResultById(id)
        // FIXME implement DAO
        BulkActionResult bulkActionResult = new BulkActionResult();

        return bulkActionResult;
    }

    @Override
    public void markInProgress(String id) {

        // get Bulk action by id BulkActionResult bar = dao.getBulkActionResultById(id)
        BulkActionResult bulkActionResult = new BulkActionResult(); // FIXME
        bulkActionResult.setBulkActionStatus(BulkActionStatus.PENDING);
        bulkActionResult.setEndProcessingDateTime(LocalDateTime.now());
    }

    @Override
    public void completeBulkAction(String bulkActionResultId, BulkActionsRunResult bulkActionsRunResult) {

        bulkActionsRunResult.getResultList(); // TODO concat to one message and set as bulkActionMessage
        // get Bulk action by id BulkActionResult bar = dao.getBulkActionResultById(id)
        // setStatus(bulkActionStatus)
        // setEndProcessingDate(new Date())
        // setDetails(bulkActionMessage)
    }

    @Override
    public void completeBulkAction(String bulkActionResultId, BulkActionStatus bulkActionStatus, String bulkActionMessage) {

        // get Bulk action by id BulkActionResult bar = dao.getBulkActionResultById(id)
        BulkActionResult bulkActionResult = new BulkActionResult(); // FIXME
        bulkActionResult.setBulkActionStatus(bulkActionStatus);
        bulkActionResult.setEndProcessingDateTime(LocalDateTime.now());
        bulkActionResult.setProcessingDetails(bulkActionMessage);
    }
}
