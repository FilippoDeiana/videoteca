package com.training.videoteca.entitity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
public class FilmEntity implements Serializable {
    private static final long serialVersionUID = 5630161389200685794L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titolo")
    private String titolo;
    @Column(name = "anno")
    private int anno;

    @ManyToOne
    @JoinColumn(name = "genere", referencedColumnName = "id")
    private GenereEntity genere;

    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JsonManagedReference
    @JoinTable(
            name = "film_interprete",
            joinColumns = @JoinColumn(name = "film"),
            inverseJoinColumns = @JoinColumn(name = "interprete"))
    private Set<InterpreteEntity> interprete = new HashSet<>();


    public FilmEntity(Long id, String titolo, int anno) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
    }

    public FilmEntity(Long id, String titolo, int anno, GenereEntity genere) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
        this.genere = genere;
    }

    public FilmEntity(Long id, String titolo, int anno, GenereEntity genere, Set<InterpreteEntity> interprete) {
        this.id = id;
        this.titolo = titolo;
        this.anno = anno;
        this.genere = genere;
        this.interprete = interprete;
    }
}
