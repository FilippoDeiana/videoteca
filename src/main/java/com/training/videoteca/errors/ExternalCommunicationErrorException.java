package com.training.videoteca.errors;

import lombok.Data;

@Data
public class ExternalCommunicationErrorException extends Exception {

    private String messaggio = "External Communication Error";

    public ExternalCommunicationErrorException() {
        super();
    }

    public ExternalCommunicationErrorException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public ExternalCommunicationErrorException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
    }
}
