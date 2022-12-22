package com.training.videoteca.pojo;

import lombok.Data;
import javax.persistence.Id;
import java.io.Serializable;


@Data
public class GenerePojo implements Serializable {
    private static final long serialVersionUID = -6799800458761294558L;

    private Long id;
    private String descrizione;

}
