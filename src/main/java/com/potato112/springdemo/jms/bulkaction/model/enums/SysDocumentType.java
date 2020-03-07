package com.potato112.springdemo.jms.bulkaction.model.enums;

public enum SysDocumentType {

    INVESTMENT_DOCUMENT("Investment amortization document"),
    CLIENT_DOCUMENT("Client history document"),
    AGREEMENT_DOCUMENT("Agreement document");

    private String fullName;

    SysDocumentType(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
