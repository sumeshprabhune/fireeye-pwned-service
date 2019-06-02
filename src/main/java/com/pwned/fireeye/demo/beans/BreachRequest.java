package com.pwned.fireeye.demo.beans;

public class BreachRequest {

    private String accountID;
    private String breachName;
    private boolean truncateResponse;
    private boolean includeUnverified;
    private String domain;

    public String getAccountID() {
        return accountID;
    }

    public boolean isTruncateResponse() {
        return truncateResponse;
    }

    public boolean isIncludeUnverified() {
        return includeUnverified;
    }

    public String getBreachName() {
        return breachName;
    }

    public String getDomain() {
        return domain;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setBreachName(String breachName) {
        this.breachName = breachName;
    }

    public void setTruncateResponse(boolean truncateResponse) {
        this.truncateResponse = truncateResponse;
    }

    public void setIncludeUnverified(boolean includeUnverified) {
        this.includeUnverified = includeUnverified;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "BreachRequest{" +
                "accountID='" + accountID + '\'' +
                ", breachName='" + breachName + '\'' +
                ", truncateResponse=" + truncateResponse +
                ", includeUnverified=" + includeUnverified +
                ", domain='" + domain + '\'' +
                '}';
    }

}
