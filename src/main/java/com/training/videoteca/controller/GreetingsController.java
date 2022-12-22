package com.training.videoteca.controller;

import com.training.videoteca.facade.GreetingsFacade;
import com.training.videoteca.pojo.GreetingsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accesso")
@CrossOrigin(origins = "http://localhost:4200/")
public class GreetingsController {

    @Autowired
    private GreetingsFacade gf;

    @GetMapping(value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetingsPojo>  getGreetings(){
       return new ResponseEntity<>(gf.descrizione(), HttpStatus.OK);
    }


}

