package com.training.videoteca.pojo.FilmPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmGenereUpdatePojo implements Serializable {
    private static final long serialVersionUID = -7142076327375774269L;
    private Long id_film;
    private Long id_genere;
}
