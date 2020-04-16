package com.potato112.springdemo.jms.bulkaction.model.entity;

import com.potato112.springdemo.jms.bulkaction.model.enums.BulkActionStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "demo-db", name = "bulkactions_results")
public class BulkActionResultMessage extends BaseEntity {

    @Id
    @Column(name = "bulkactions_results_id", length = 50)
    @GeneratedValue(generator = "seq_id")
    @GenericGenerator(name = "seq_id", strategy = "identity")
    private String id;

    @ManyToOne
    @JoinColumn(name = "bulkactions_id")
    private BulkActionResult bulkActionResult;

    @Enumerated(EnumType.STRING)
    private BulkActionStatus bulkActionStatus;

    private String messageContent;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public BulkActionResult getBulkActionResult() {
        return bulkActionResult;
    }

    public void setBulkActionResult(BulkActionResult bulkActionResult) {
        this.bulkActionResult = bulkActionResult;
    }

    public BulkActionStatus getBulkActionStatus() {
        return bulkActionStatus;
    }

    public void setBulkActionStatus(BulkActionStatus bulkActionStatus) {
        this.bulkActionStatus = bulkActionStatus;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
