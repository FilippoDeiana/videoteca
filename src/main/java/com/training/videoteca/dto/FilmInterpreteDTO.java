package com.training.videoteca.dto;

import com.training.videoteca.entitity.InterpreteEntity;
import lombok.Data;
import java.util.List;


@Data
public class FilmInterpreteDTO {

    private Long id;
    private String titolo;
    private int anno;
    private List<InterpreteEntity> interprete;

    public FilmInterpreteDTO(Long id, String titolo, int anno, List<InterpreteEntity> interprete) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
        this.interprete = interprete;
    }
}
