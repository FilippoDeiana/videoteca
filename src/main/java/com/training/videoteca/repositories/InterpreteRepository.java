package com.training.videoteca.repositories;

import com.training.videoteca.entitity.InterpreteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterpreteRepository extends JpaRepository<InterpreteEntity, Long> {

    InterpreteEntity findInterpreteById(Long id);
    List<InterpreteEntity> findByNomeIgnoreCaseContains(String nome);
    List<InterpreteEntity> findByCognomeIgnoreCaseContains(String cognome);
    List<InterpreteEntity> findByNomeIgnoreCaseContainsOrCognomeIgnoreCaseContains(String nome, String cognome);

    List<InterpreteEntity> findAll();

    InterpreteEntity save(InterpreteEntity i);

    InterpreteEntity findInterpreteByNomeIgnoreCase(String nome);
    InterpreteEntity findInterpreteByCognomeIgnoreCase(String cognome);

}

