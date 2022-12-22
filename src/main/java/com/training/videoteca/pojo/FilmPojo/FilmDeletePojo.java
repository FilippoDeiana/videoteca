package com.training.videoteca.pojo.FilmPojo;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.entitity.InterpreteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class FilmDeletePojo implements Serializable {
    private static final long serialVersionUID = 1535645554253675686L;
    private Long id;
    private String titolo;
    private int anno;


}
