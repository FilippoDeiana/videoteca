package com.training.videoteca.pojo.FilmPojo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class FilmAddInterpretePojo implements Serializable {
    private static final long serialVersionUID = 818941183611578109L;
    private String id_film;
    private Set<Long> id_interprete = new HashSet<>();
}
