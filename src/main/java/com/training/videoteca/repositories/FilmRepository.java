package com.training.videoteca.repositories;


import com.training.videoteca.dto.FilmDTO;
import com.training.videoteca.dto.FilmGenereDTO;
import com.training.videoteca.dto.FilmIdDTO;
import com.training.videoteca.entitity.FilmEntity;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {


    @Query("SELECT new com.training.videoteca.dto.FilmDTO(f.id, f.titolo, f.anno) FROM FilmEntity f")
    List<FilmDTO> getListaTitoli();

    @Query("SELECT new com.training.videoteca.dto.FilmIdDTO(f.id) FROM FilmEntity f ORDER BY f.id")
    Page<FilmIdDTO> getListaTitoliId(Pageable paging);
    @Query("SELECT new com.training.videoteca.dto.FilmDTO(f.id, f.titolo, f.anno) FROM FilmEntity f WHERE id=:id")
    FilmDTO findTitoloById (@Param("id") Long id);
    @Query("SELECT new com.training.videoteca.dto.FilmDTO(f.id, f.titolo, f.anno) FROM FilmEntity f WHERE titolo=:nome")
    FilmDTO findFilmByNome (@Param("nome") String nome);
    @Query("SELECT f FROM FilmEntity f WHERE titolo LIKE %:nome%")
    List<FilmEntity> findFilmByNomeCompleto (@Param("nome") String nome);
    @Query("SELECT new com.training.videoteca.dto.FilmDTO(f.id, f.titolo, f.anno) FROM FilmEntity f WHERE titolo LIKE %:nome%")
    List<FilmDTO> findTitoloByNome(@Param("nome")String nome);
    @Query("SELECT new com.training.videoteca.dto.FilmDTO(f.id, f.titolo, f.anno) FROM FilmEntity f WHERE anno=:anno")
    List<FilmDTO> findTitoloByAnno (@Param("anno")int anno);
    // modificare query in jpa, solo lasciando il nome e togliendo query
    @Query("SELECT new com.training.videoteca.dto.FilmGenereDTO(f.id, f.titolo, f.anno, f.genere) FROM FilmEntity f  WHERE descrizione=:genre")
    List<FilmGenereDTO> findTitoloByGenere (@Param("genre")String genere);

    @Query("SELECT f from FilmEntity f JOIN f.interprete i where i.nome like %:name% or i. cognome like %:name%")
    List<FilmEntity> findTitoloByInterprete (@Param("name")String nome);

    @Query("SELECT f from FilmEntity f group by f")
    List<FilmEntity> findListaAll();
    @Query("SELECT f from FilmEntity f where f.id=:id")
    FilmEntity findTitoloCompletoById (@Param("id") Long id);

    FilmEntity save (FilmEntity f);
    void deleteById (Long id);


}
