package com.decathlon.alert.request;

import org.springframework.stereotype.Component;

@Component
public class AlertRequest {
    private String phoneNumer;

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }
}
