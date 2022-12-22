package com.training.videoteca.pojo.FilmPojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmUpdatePojo implements Serializable {
    private static final long serialVersionUID = 1141312147147982039L;

    private Long id;
    private String titolo;
    private String anno;
}
