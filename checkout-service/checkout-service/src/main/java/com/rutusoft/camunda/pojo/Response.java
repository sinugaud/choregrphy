package com.rutusoft.camunda.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Response {
    private int status;
    private String message;
    private Object data;
    private String exception;

    public Response() {}

    public Response(int status, String message, Object data, String exception) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
