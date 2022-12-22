package com.training.videoteca.services.implementation;

import com.training.videoteca.dto.FilmDTO;
import com.training.videoteca.dto.FilmGenereDTO;
import com.training.videoteca.dto.FilmIdDTO;

import com.training.videoteca.entitity.FilmEntity;

import com.training.videoteca.repositories.FilmRepository;
import com.training.videoteca.services.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository fr;

    @Override
    public List<FilmDTO> getListaTitoli() {
        return fr.getListaTitoli();
    }

    @Override
    public List<Long> getListaTitoliId(){
        Pageable paging = PageRequest.of(0, 5);
        

        List<Long> films = fr.getListaTitoliId(paging).stream().map(FilmIdDTO :: getId).collect(Collectors.toList());

        return films;
    }

    @Override
    public FilmDTO findTitoloById(Long id) {
        return fr.findTitoloById(id);
    }

    @Override
    public List<FilmDTO> findTitoloByNome(String nome) {
        return fr.findTitoloByNome(nome);
    }

    @Override
    public FilmDTO findFilmByNome(String nome) {
        return fr.findFilmByNome(nome);
    }

    @Override
    public List<FilmEntity> findFilmByNomeCompleto(String nome) {
        return fr.findFilmByNomeCompleto(nome);
    }


    @Override
    public List<FilmDTO> findTitoloByAnno(int anno) {
        return fr.findTitoloByAnno(anno);
    }

    @Override
    public List<FilmGenereDTO> findTitoloByGenere(String genere) {
        return fr.findTitoloByGenere(genere);
    }


    @Override
    public List<FilmEntity> findListaAll() {
        return fr.findListaAll();
    }

    @Override
    public FilmEntity findTitoloCompletoById(Long id) {
        return fr.findTitoloCompletoById(id);
    }

    @Override
    public List<FilmEntity> findInterpreteByNome(String nome) {
        return fr.findTitoloByInterprete(nome);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       fr.deleteById(id);
    }

    @Override
    @Transactional
    public FilmEntity save(FilmEntity f) {
        return fr.save(f);
    }
}
