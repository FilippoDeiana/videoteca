package com.training.videoteca.pojo.FilmPojo;


import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class FilmSavePojo implements Serializable {
    private static final long serialVersionUID = -4438831356738198241L;
//modificare anno, ids in stringhe per il controllo
    private Long id;
    private String titolo;
    private String anno;
    private Long id_genere;
    private Set<Long> id_interprete = new HashSet<>();

}
