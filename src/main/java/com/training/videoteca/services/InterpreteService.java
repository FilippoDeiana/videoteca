package com.training.videoteca.services;

import com.training.videoteca.entitity.InterpreteEntity;

import java.util.List;

public interface InterpreteService {
    InterpreteEntity findInterpreteById(Long id);
    List<InterpreteEntity> findByNome(String nome);
    List<InterpreteEntity> findByCognome(String cognome);
    List<InterpreteEntity> findByFullName(String fullname);
    List<InterpreteEntity> findAll();
    InterpreteEntity save(InterpreteEntity g);
    InterpreteEntity update(InterpreteEntity g);

    InterpreteEntity findInterpreteByNomeIgnoreCase(String nome);
    InterpreteEntity findInterpreteByCognomeIgnoreCase(String cognome);

}
