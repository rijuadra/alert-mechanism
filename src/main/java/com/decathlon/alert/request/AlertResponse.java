package com.decathlon.alert.request;

public class AlertResponse {
    private String status;
    private String alertedPhoneNumber;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlertedPhoneNumber() {
        return alertedPhoneNumber;
    }

    public void setAlertedPhoneNumber(String alertedPhoneNumber) {
        this.alertedPhoneNumber = alertedPhoneNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", alertedPhoneNumber='" + alertedPhoneNumber + '\'' +
                '}';
    }
}
