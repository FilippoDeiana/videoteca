package com.training.videoteca.controller;

import com.training.videoteca.facade.GenereFacade;
import com.training.videoteca.pojo.GenerePojo;
import com.training.videoteca.pojo.response.GeneriResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generi")
@CrossOrigin(origins = "http://localhost:4200/")
public class GenereController {

    @Autowired
    private GenereFacade gf;

    @GetMapping(value = "/listaGeneri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGeneri() {
        GeneriResponse gr = gf.securityAll();

        if(gr.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(gr.getList(), gr.getErrorResponse().getStatus());

        } else {
            return new ResponseEntity<>(gr.getErrorResponse(), gr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = {"/genere/{id}", "/genere"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenereById(@PathVariable(name = "id", required = false) String id) {
        GeneriResponse gr = gf.securityId(id);

        if(gr.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(gr.getGeneriEntity(), gr.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(gr.getErrorResponse(), gr.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/descrizione", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenereByDescrizione(@RequestParam(name = "genere") String descrizione) {
        GeneriResponse gr = gf.securityByDescrizioneLike(descrizione);

        if(gr.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(gr.getList(), gr.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(gr.getErrorResponse(), gr.getErrorResponse().getStatus());
        }
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newGenere(@RequestBody GenerePojo gp) {
        GeneriResponse gr = gf.save(gp);

        if(gr.getErrorResponse().getStatus() == HttpStatus.NO_CONTENT){
            return new ResponseEntity<>(gr.getGeneriEntity(), gr.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(gr.getErrorResponse(), gr.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/genere/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGenereById(@RequestBody GenerePojo gp) {
        GeneriResponse gr = gf.updateById(gp);

        if(gr.getErrorResponse().getStatus() == HttpStatus.NO_CONTENT){
            return new ResponseEntity<>(gr.getGeneriEntity(), gr.getErrorResponse().getStatus());
        }else {
            return new ResponseEntity<>(gr.getErrorResponse(), gr.getErrorResponse().getStatus());
        }
    }
}
