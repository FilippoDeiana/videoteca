package com.training.videoteca.pojo.SalePojo;

import com.training.videoteca.entitity.FilmEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalePojo implements Serializable {
     private static final long serialVersionUID = 2129984319843863856L;
     private Long id;
     private int nome;
     private String descrizione;


}
