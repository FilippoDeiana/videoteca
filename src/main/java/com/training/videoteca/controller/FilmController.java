package com.training.videoteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.facade.FilmFacade;
import com.training.videoteca.pojo.FilmPojo.*;
import com.training.videoteca.pojo.response.FilmResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping("/api/films")
@CrossOrigin(origins = "http://localhost:4200/")
public class FilmController {

    private static final String NOTFOUND = "Film non trovato";
    private static final String ERROR ="Errore nella ricerca";
    private static final String DATINOTVALID ="Dati inseriti non validi";
    @Autowired
    private FilmFacade ff;



    @GetMapping(value = "/listaFilms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilms() {
        FilmResponse fr = ff.securityAll();

        if (fr.getErrorResponse().getStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(fr.getListDTO(), fr.getErrorResponse().getStatus());

        } else {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = {"/listaAll"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListaTitoliCompleta() {
        FilmResponse fr = ff.securityListaAll();

        if (fr.getErrorResponse().getStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(fr.getList(), fr.getErrorResponse().getStatus());

        } else {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/listaFilmsId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmsId() {
        FilmResponse fr = ff.securityAllId();

        if (fr.getErrorResponse().getStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(fr.getListIdDto(), fr.getErrorResponse().getStatus());

        } else {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = {"/film/{id}", "/film"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmById(@PathVariable(name = "id", required = false) String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.securityId(id);
            return new ResponseEntity<>(fr.getFilmDTO(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @GetMapping(value = "/cerca/nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloByNome(@RequestParam(name = "nome") String nome) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();
        try {
            fr = ff.securityByNome(nome);
            return new ResponseEntity<>(fr.getListDTO(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/cerca/nomeCompleto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloCompletoByNome(@RequestParam(name = "nome") String nome) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();
        try {
            fr = ff.securityByNomeCompleto(nome);
            return new ResponseEntity<>(fr.getList(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/cerca/anno", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloByAnno(@RequestParam(name = "anno") String anno) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();
        try {
            fr = ff.securityByAnno(anno);
            return new ResponseEntity<>(fr.getListDTO(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/cerca/genere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloByGenere(@RequestParam(name = "genere") String genere) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();
        try {
            fr = ff.securityByGenere(genere);
            return new ResponseEntity<>(fr.getListGenereDTO(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/cerca/interprete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTitoloByInterprete(@RequestParam(name = "nome") String nome) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();
        try {
            fr = ff.securityByInterprete(nome);
            return new ResponseEntity<>(fr.getList(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }



    @GetMapping(value = {"/filmcompleto/{id}", "/filmcompleto"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmCompletoById(@PathVariable(name = "id", required = false) String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.securityTitoloCompletoById(id);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(NOTFOUND, e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }
    }


    @PostMapping(value = {"/new"}, produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newFilm(@RequestBody FilmSavePojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.save(fp);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Titolo già esistente", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/film/update", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFilm(@RequestBody FilmUpdatePojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.update(fp);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Titolo già esistente", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/film/genere/update", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGenereFilm(@RequestBody FilmGenereUpdatePojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.updateGenere(fp);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Titolo o genere non esistenti", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/film/delete", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFilm(@RequestBody FilmIdPojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.deleteFilm(fp);
            return new ResponseEntity<>(fr.getFilmDeletePojo(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException("Titolo non presente", e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/film/add/interprete", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addInterpreteFilm(@RequestBody FilmAddInterpretePojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.addInterprete(fp);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(e.getMessaggio(), e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/film/remove/interprete", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInterpreteFilm(@RequestBody FilmInterpretePojo fp) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse fr = new FilmResponse();

        try {
            fr = ff.removeInterprete(fp);
            return new ResponseEntity<>(fr.getFilmEntity(), fr.getErrorResponse().getStatus());
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(ERROR, e);
        } catch (NotValidException e) {
            throw new NotValidException(e.getMessaggio(), e);
        } catch (NotFoundException e) {
            throw new NotFoundException(DATINOTVALID, e);
        } catch (Exception e) {
            return new ResponseEntity<>(fr.getErrorResponse(), fr.getErrorResponse().getStatus());
        }

    }
}
