package com.training.videoteca.dto;

import com.training.videoteca.entitity.GenereEntity;
import lombok.Data;


@Data
public class FilmGenereDTO {

    private Long id;
    private String titolo;
    private int anno;
    private GenereEntity genere;


    public FilmGenereDTO(Long id, String titolo, int anno, GenereEntity genere) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
        this.genere = genere;
    }
}
