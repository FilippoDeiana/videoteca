package com.training.videoteca.errors;

import lombok.Data;

@Data
public class UnauthorizedException extends Exception{

    private String messaggio = "Unauthorized Exception";

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public UnauthorizedException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
    }
}
