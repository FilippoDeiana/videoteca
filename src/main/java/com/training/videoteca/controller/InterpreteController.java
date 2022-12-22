package com.training.videoteca.controller;

import com.training.videoteca.facade.InterpreteFacade;
import com.training.videoteca.pojo.InterpretePojo;
import com.training.videoteca.pojo.response.InterpreteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interpreti")
@CrossOrigin(origins = "http://localhost:4200/")
public class InterpreteController {


    @Autowired
    private InterpreteFacade interpreteFacade;

    @GetMapping(value = "/listaInterpreti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreti() {
        InterpreteResponse interpreteResponse = interpreteFacade.securityAll();

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getList(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }
    //
    @GetMapping(value = {"/interprete/{id}", "/interprete"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteById(@PathVariable(name = "id", required = false) String id) {
        InterpreteResponse interpreteResponse = interpreteFacade.securityId(id);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getInterpreteEntity(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }

    @GetMapping(value = "/cerca/nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteByNome(@RequestParam(name = "nome") String nome) {
        InterpreteResponse interpreteResponse = interpreteFacade.securityByNomeLike(nome);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getList(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }
    @GetMapping(value = "/cerca/cognome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteByCognome(@RequestParam(name = "cognome") String cognome) {
        InterpreteResponse interpreteResponse = interpreteFacade.securityByCognomeLike(cognome);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getList(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }


    @GetMapping(value = "/cerca/fullname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteByNomeAndCognome(@RequestParam(name = "nome") String fullname) {
        InterpreteResponse interpreteResponse = interpreteFacade.securityByFullname(fullname);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getList(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newInterprete(@RequestBody InterpretePojo ip) {
        InterpreteResponse interpreteResponse = interpreteFacade.save(ip);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.NO_CONTENT){
            return new ResponseEntity<>(interpreteResponse.getInterpreteEntity(), interpreteResponse.getErrorResponse().getStatus());

        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }

    }

    @PostMapping(value = "/interprete/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateInterpreteById(@RequestBody InterpretePojo ip) {
        InterpreteResponse interpreteResponse = interpreteFacade.updateById(ip);

        if(interpreteResponse.getErrorResponse().getStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getInterpreteEntity(), interpreteResponse.getErrorResponse().getStatus());
        }else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getStatus());
        }
    }
}
