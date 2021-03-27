package com.decathlon.alert.advice;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private String errorMsg;
    private LocalDateTime localDateTime;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
