package com.potato112.springdemo.jms.doclock;

import com.potato112.springdemo.jms.bulkaction.model.entity.BaseEntity;
import com.potato112.springdemo.jms.bulkaction.model.enums.SysDocumentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
        // TODO

})
public class DocLock extends BaseEntity {

    @Id
    @GeneratedValue(generator = "seq_id")
    @GenericGenerator(name = "seq_id", strategy = "identity")
    private String id;
    private String login;

    @Enumerated(EnumType.STRING)
    private SysDocumentType documentType;
    private String docId;
    private String docCode;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public SysDocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(SysDocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }
}
