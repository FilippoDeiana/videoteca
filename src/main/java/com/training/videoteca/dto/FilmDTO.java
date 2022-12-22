package com.training.videoteca.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {

    private Long id;
    private String titolo;
    private int anno;

    public FilmDTO(String titolo, int anno) {
        this.titolo = titolo;
        this.anno = anno;
    }


    public FilmDTO(Long id, String titolo, int anno) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
    }


}
