package com.training.videoteca.pojo.SalePojo;

import com.training.videoteca.entitity.FilmEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleCompletoPojo  implements Serializable {
    private static final long serialVersionUID = -3511849717100635892L;
    private Long id;
    private int nome;
    private String descrizione;
    private Long id_film;
}
