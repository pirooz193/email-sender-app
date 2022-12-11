package com.selfproject.emailapp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDetailsDto {

    @JsonProperty("sender")
    private String sender;
    @JsonProperty("receiver")
    private String recipient;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("body")
    private String body;
    @JsonProperty("attachment")
    private byte[] attachment;

    @Override
    public String toString() {
        return "EmailDetailsDto{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDetailsDto that = (EmailDetailsDto) o;
        return sender.equals(that.sender) && recipient.equals(that.recipient) && subject.equals(that.subject) && body.equals(that.body) && attachment.equals(that.attachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, subject, body, attachment);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
