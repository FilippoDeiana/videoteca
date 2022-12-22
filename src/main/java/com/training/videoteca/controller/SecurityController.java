package com.training.videoteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.training.videoteca.security.WindowsLoginToken;
import com.training.videoteca.errors.ExternalCommunicationErrorException;
import com.training.videoteca.facade.SecurityFacade;
import com.training.videoteca.pojo.response.SecurityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/accesso/")
@CrossOrigin(origins = "http://localhost:4200/")
public class SecurityController {

    @Autowired
    private SecurityFacade sf;

    @GetMapping(value = "/security", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        SecurityResponse sr = sf.security(username, password);

        if (sr.getErrorResponse().getStatus() == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(sr.getSecurityPojo(), sr.getErrorResponse().getStatus());

        } else {
            return new ResponseEntity<>(sr.getErrorResponse(), sr.getErrorResponse().getStatus());
        }

    }

    @GetMapping("/authorize")
    @ResponseBody
    public WindowsLoginToken Admin() throws IOException, ExternalCommunicationErrorException {

        return sf.authorize();
    }

}


