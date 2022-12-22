package com.training.videoteca.errors;

import lombok.Data;

@Data
public class InternalServerErrorException extends Exception{

    private String messaggio = "Internal Server Error";

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public InternalServerErrorException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
    }
}
