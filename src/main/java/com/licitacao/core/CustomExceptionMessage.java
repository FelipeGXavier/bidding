package com.licitacao.core;

public class CustomExceptionMessage {

    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public CustomExceptionMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public CustomExceptionMessage setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
