package com.training.videoteca.entitity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;




@Entity
@Table(name = "interprete")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpreteEntity implements Serializable {
    private static final long serialVersionUID = -74400671745018741L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;


}
