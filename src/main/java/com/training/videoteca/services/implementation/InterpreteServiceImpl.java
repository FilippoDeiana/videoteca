package com.training.videoteca.services.implementation;

import com.training.videoteca.entitity.InterpreteEntity;
import com.training.videoteca.repositories.InterpreteRepository;
import com.training.videoteca.services.InterpreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InterpreteServiceImpl implements InterpreteService {
    @Autowired
    private InterpreteRepository ir;

    @Override
    public InterpreteEntity findInterpreteById(Long id) {
        return ir.findInterpreteById(id);
    }

    @Override
    public List<InterpreteEntity> findByNome(String nome) {
        return ir.findByNomeIgnoreCaseContains(nome);
    }

    @Override
    public List<InterpreteEntity> findByCognome(String cognome) {
        return ir.findByCognomeIgnoreCaseContains(cognome);
    }

    @Override
    public List<InterpreteEntity> findByFullName(String fullname) {
        return ir.findByNomeIgnoreCaseContainsOrCognomeIgnoreCaseContains(fullname, fullname);
    }

    @Override
    public List<InterpreteEntity> findAll() {
        return ir.findAll();
    }



    @Override
    @Transactional
    public InterpreteEntity save(InterpreteEntity i) {
        return ir.save(i);
    }

    @Override
    @Transactional
    public InterpreteEntity update(InterpreteEntity i) {
        return ir.save(i);
    }

    @Override
    public InterpreteEntity findInterpreteByNomeIgnoreCase(String nome) {
        return ir.findInterpreteByNomeIgnoreCase(nome);
    }

    @Override
    public InterpreteEntity findInterpreteByCognomeIgnoreCase(String cognome) {
        return ir.findInterpreteByCognomeIgnoreCase(cognome);
    }
}
