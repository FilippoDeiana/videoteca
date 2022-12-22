package com.training.videoteca.services;

import com.training.videoteca.dto.FilmDTO;
import com.training.videoteca.dto.FilmGenereDTO;
import com.training.videoteca.dto.FilmIdDTO;
import com.training.videoteca.dto.FilmInterpreteDTO;
import com.training.videoteca.entitity.FilmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface FilmService {
    List<FilmDTO> getListaTitoli();

    List<Long> getListaTitoliId();
    FilmDTO findTitoloById (Long id);
    List<FilmDTO> findTitoloByNome (String nome);
    FilmDTO findFilmByNome (String nome);

    List<FilmEntity> findFilmByNomeCompleto (String nome);
    List<FilmDTO> findTitoloByAnno (int anno);
    List<FilmGenereDTO> findTitoloByGenere (String genere);
    List<FilmEntity> findListaAll();
    FilmEntity findTitoloCompletoById (Long id);
    List<FilmEntity> findInterpreteByNome(String nome);
    void deleteById (Long id);
    FilmEntity save (FilmEntity f);

}
