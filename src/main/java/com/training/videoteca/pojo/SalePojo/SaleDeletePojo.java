package com.training.videoteca.pojo.SalePojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class SaleDeletePojo implements Serializable {
    private static final long serialVersionUID = -8043585495648566455L;
    private Long id;
    private int nome;
    private String descrizione;
}
