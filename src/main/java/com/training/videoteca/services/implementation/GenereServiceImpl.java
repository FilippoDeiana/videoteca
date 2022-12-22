package com.training.videoteca.services.implementation;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.repositories.GenereRepository;
import com.training.videoteca.services.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GenereServiceImpl implements GenereService {
    @Autowired
    private GenereRepository gr;

    @Override
    public GenereEntity findGenereById(Long id) {

        return gr.findGenereById(id);
    }

    @Override
    public List<GenereEntity> findAll() {

        return gr.findAll();
    }

    @Override
    public List<GenereEntity> findByDescrizioneContains(String descrizione) {
        return gr.findByDescrizioneIgnoreCaseContains(descrizione);

    }

    @Override
    @Transactional
    public GenereEntity save(GenereEntity g) {
        return gr.save(g);

    }
    @Override
    public GenereEntity getGenereByDescrizione(String descrizione) {

        return gr.getGenereIgnoreCaseByDescrizione(descrizione);
    }

    @Override
    @Transactional
    public GenereEntity update(GenereEntity g) {
        return gr.save(g);
    }


}
