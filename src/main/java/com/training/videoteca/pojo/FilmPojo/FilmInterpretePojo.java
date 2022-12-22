package com.training.videoteca.pojo.FilmPojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmInterpretePojo implements Serializable {
    private static final long serialVersionUID = 818941183611578109L;
    private String id_film;
    private String id_interprete;
}
