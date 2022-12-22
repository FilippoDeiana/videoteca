package com.training.videoteca.services;

import com.training.videoteca.entitity.GenereEntity;
import java.util.List;

public interface GenereService {
    GenereEntity findGenereById(Long id);
    List<GenereEntity> findAll();
    List<GenereEntity> findByDescrizioneContains(String descrizione);
    GenereEntity save(GenereEntity g);

    GenereEntity getGenereByDescrizione(String descrizione);
    GenereEntity update(GenereEntity g);
}
