package com.training.videoteca.repositories;

import com.training.videoteca.entitity.GenereEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GenereRepository extends JpaRepository<GenereEntity, Long> {

    GenereEntity findGenereById(Long id);
    List<GenereEntity> findByDescrizioneIgnoreCaseContains(String descrizione);
    List<GenereEntity> findAll();

    GenereEntity save(GenereEntity g);
    GenereEntity getGenereIgnoreCaseByDescrizione(String descrizione);

}
