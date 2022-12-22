package com.training.videoteca.controller;

import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.facade.SaleFacade;
import com.training.videoteca.pojo.SalePojo.SaleCompletoPojo;
import com.training.videoteca.pojo.SalePojo.SaleIdPojo;
import com.training.videoteca.pojo.SalePojo.SalePojo;
import com.training.videoteca.pojo.response.SaleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/sale")
@CrossOrigin(origins = "http://localhost:4200/")
public class SaleController {

    private static final String ERROR ="Errore nella ricerca";
    private static final String DATINOTVALID ="Dati inseriti non validi";
    @Autowired
    private SaleFacade saleFacade;

    @GetMapping(value = "/listaSale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilms() throws NotFoundException, InternalServerErrorException {
        SaleResponse sr = new SaleResponse();
        try {
          sr = saleFacade.securityAll();
            return new ResponseEntity<>(sr.getSaleEntities(), sr.getErrorResponse().getStatus());
        } catch (NotFoundException e) {
            throw new NotFoundException("Non ci sono sale presenti");
        } catch (Exception e) {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }

    @GetMapping(value = {"/sala/{id}", "/sala"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSalaById(@PathVariable(name = "id", required = false) String id) throws NotFoundException, InternalServerErrorException {
        SaleResponse sr = new SaleResponse();
        try {
            sr = saleFacade.securityId(id);
            return new ResponseEntity<>(sr.getSaleEntity(), sr.getErrorResponse().getStatus());
        } catch (NotFoundException e) {
            throw new NotFoundException("Non ci sono sale presenti");
        } catch (Exception e) {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }


    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newSala(@RequestBody SaleCompletoPojo sp) throws InternalServerErrorException, NotValidException, NotFoundException {
        SaleResponse sr = new SaleResponse();

        try {
            sr = saleFacade.save(sp);
            return new ResponseEntity<>(sr.getSaleEntity(), sr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Sala già esistente", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSala(@RequestBody SaleIdPojo sp) throws InternalServerErrorException, NotValidException, NotFoundException {
        SaleResponse sr = new SaleResponse();

        try {
            sr = saleFacade.deleteById(sp);
            return new ResponseEntity<>(sr.getSaleDeletePojo(), sr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Sala non valida", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSala(@RequestBody SaleCompletoPojo sp) throws InternalServerErrorException, NotValidException, NotFoundException {
        SaleResponse sr = new SaleResponse();

        try {
            sr = saleFacade.update(sp);
            return new ResponseEntity<>(sr.getSaleEntity(), sr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Sala non valida", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }
}
