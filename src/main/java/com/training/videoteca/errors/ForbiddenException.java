package com.training.videoteca.errors;

import lombok.Data;

@Data
public class ForbiddenException extends Exception{

    private String messaggio = "Forbidden";

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public ForbiddenException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
    }
}
