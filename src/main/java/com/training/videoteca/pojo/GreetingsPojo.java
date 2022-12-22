package com.training.videoteca.pojo;


import java.io.Serializable;

public class GreetingsPojo implements Serializable {

    private static final long serialVersionUID = -5674950675674307228L;
    private String descrizione;

    public GreetingsPojo(String descrizione) {

        this.descrizione = descrizione;
    }

    public GreetingsPojo() {

    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "GreetingsPojo{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }


}
