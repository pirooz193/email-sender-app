package com.selfproject.emailapp.web.error;


import java.util.Objects;

public class ExceptionHandlerClass {

    private String title;
    private String message;
    private int statusCode;

    public ExceptionHandlerClass() {
    }

    public ExceptionHandlerClass(String title, String message, int statusCode) {
        this.title = title;
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ExceptionHandler{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionHandlerClass that = (ExceptionHandlerClass) o;
        return statusCode == that.statusCode && title.equals(that.title) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, message, statusCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
