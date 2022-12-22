package com.training.videoteca.errors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NotValidException extends Exception{
    private String messaggio = "Informazione inserita non valida";

    public NotValidException () {
        super();
    }

    public NotValidException (String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public NotValidException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
    }
}

