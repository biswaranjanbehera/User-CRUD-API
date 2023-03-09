package com.api.user.apiuser.entities;

public class Response {
    private boolean status;
    private String message;
    private Object Payload;

    public Response(){

    }

    public Response(boolean status, String message, Object payload) {
        this.status = status;
        this.message = message;
        Payload = payload;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return Payload;
    }

    public void setPayload(Object payload) {
        Payload = payload;
    }
}
