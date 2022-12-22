package com.training.videoteca.entitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="genere")
@Data
public class GenereEntity implements Serializable {

    private static final long serialVersionUID = 8821326882461755660L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descrizione", unique = true)
    private String descrizione;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genere")
    @JsonBackReference
    private Set<FilmEntity> films = new HashSet<>();


    public GenereEntity() {
    }

    public GenereEntity(String descrizione) {

        this.descrizione = descrizione;
    }

    public GenereEntity(Long id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenereEntity genereEntity = (GenereEntity) o;
        return Objects.equals(descrizione, genereEntity.descrizione);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Genere{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
