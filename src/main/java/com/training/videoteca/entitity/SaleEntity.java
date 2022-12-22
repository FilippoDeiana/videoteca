package com.training.videoteca.entitity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sale")

public class SaleEntity implements Serializable {
    private static final long serialVersionUID = -945692045785722648L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private int nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "film", referencedColumnName = "id")
    private FilmEntity film;

    public SaleEntity (){

    }

    public SaleEntity(int nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public SaleEntity(int nome, String descrizione, FilmEntity film) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public FilmEntity getFilm() {
        return film;
    }

    public void setFilm(FilmEntity film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleEntity that = (SaleEntity) o;
        return nome == that.nome && Objects.equals(id, that.id) && Objects.equals(descrizione, that.descrizione) && Objects.equals(film, that.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descrizione, film);
    }

    @Override
    public String toString() {
        return "SaleEntity{" +
                "id=" + id +
                ", nome=" + nome +
                ", descrizione='" + descrizione + '\'' +
                ", film=" + film +
                '}';
    }
}
