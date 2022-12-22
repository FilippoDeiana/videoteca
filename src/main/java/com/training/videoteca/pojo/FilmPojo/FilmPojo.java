package com.training.videoteca.pojo.FilmPojo;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.entitity.InterpreteEntity;
import lombok.Data;
import java.io.Serializable;
import java.util.Set;

@Data
public class FilmPojo implements Serializable {
    private static final long serialVersionUID = -3980845361227468970L;

    private Long id;
    private String titolo;
    private int anno;
    private GenereEntity genere;
    private Set<InterpreteEntity> interprete;
}
