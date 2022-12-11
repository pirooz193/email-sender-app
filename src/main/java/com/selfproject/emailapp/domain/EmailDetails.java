package com.selfproject.emailapp.domain;


import java.util.Arrays;

public class EmailDetails {

    private long id;
    private String recipient;
    private String body;
    private String subject;
    private byte[] attachment;

    @Override
    public String toString() {
        return "EmailDetails{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", attachment='" + Arrays.toString(attachment) + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
