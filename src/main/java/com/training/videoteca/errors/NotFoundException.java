package com.training.videoteca.errors;

import lombok.Data;

@Data
public class NotFoundException extends Exception{
    private String messaggio = "Elemento ricercato non trovato";

    public NotFoundException() {
       super();
    }

    public NotFoundException(String messaggio) {
        super(messaggio);
        this.messaggio = messaggio;
    }

    public NotFoundException (String messaggio, Throwable innerException) {
        super(messaggio, innerException);
        this.messaggio = messaggio;
      }
  }





