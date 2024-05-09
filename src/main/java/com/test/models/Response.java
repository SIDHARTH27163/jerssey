package com.test.models;
public class Response {
    private String status;
    private String message;

    // Constructor
    public Response() {
        // Initialize default values or leave them null
    }

    // Getters and setters for status and message
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
