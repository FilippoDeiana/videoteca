package com.training.videoteca.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterpretePojo implements Serializable {
    private static final long serialVersionUID = 4621789308796050023L;

    private Long id;
    private String nome;
    private String cognome;
}
