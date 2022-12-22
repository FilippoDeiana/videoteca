package com.training.videoteca.facade;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.GenerePojo;
import com.training.videoteca.pojo.response.GeneriResponse;
import com.training.videoteca.services.implementation.GenereServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class GenereFacade {

    private static final String NOTFOUND = "Genere non trovato";
    private static final String INVALID = "Dati invalidi";
    private static final String ERROR ="Errore nella ricerca";
    @Autowired
    private GenereServiceImpl genereService;


    public GeneriResponse securityId(String id) {
        GenereEntity genereEntity = new GenereEntity();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (id != null && !id.isEmpty()) {
            try {
                genereEntity = genereService.findGenereById(Long.valueOf(id));
                if (genereEntity == null) {
                    errorResponse.setStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMessaggio(NOTFOUND);
                } else {
                    errorResponse.setStatus(HttpStatus.OK);
                }
            } catch (Exception e) {
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMessaggio(ERROR);

            }
        } else {
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(INVALID);
        }
        return new GeneriResponse(genereEntity, errorResponse, null);
    }


    public GeneriResponse securityAll() {
        List<GenereEntity> genereEntity = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        try {
            genereEntity = genereService.findAll();
        if (genereEntity != null && !genereEntity.isEmpty()) {
            errorResponse.setStatus(HttpStatus.OK);
        } else {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMessaggio("Non è presente nessun genere");
        }

        } catch (Exception e){
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(INVALID);
        }
        return new GeneriResponse(null, errorResponse, genereEntity);
    }

    public GeneriResponse securityByDescrizioneLike(String descrizione) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<GenereEntity> list = new ArrayList<>();


        if (descrizione.length() >= 3) {
            try {
                list = genereService.findByDescrizioneContains(descrizione);
                if (list == null || list.isEmpty()) {
                    errorResponse.setStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMessaggio(NOTFOUND);
                } else {
                    errorResponse.setStatus(HttpStatus.OK);
                }
            } catch (Exception e) {
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMessaggio(ERROR);

            }
        } else {
            errorResponse.setStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMessaggio(INVALID);
        }
        return new GeneriResponse(null, errorResponse, list);
    }


    public GeneriResponse save(GenerePojo gp) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        GenereEntity genereEntity = new GenereEntity(gp.getDescrizione());

        String descr = validDescrizione(gp.getDescrizione());

        if (gp.getDescrizione() != null && !gp.getDescrizione().isEmpty()) {
            try {
                if (descr.equals(gp.getDescrizione())) {
                    errorResponse.setStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMessaggio("Genere già esistente");
                } else {
                    genereService.save(genereEntity);
                    errorResponse.setStatus(HttpStatus.NO_CONTENT);
                }
            } catch (Exception e) {
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMessaggio(ERROR);

            }
        } else {
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(INVALID);
        }
        return new GeneriResponse(genereEntity, errorResponse, null);

    }

    public String validDescrizione(String descrizione){
        if (genereService.getGenereByDescrizione(descrizione) != null){
            return descrizione;
        } else {
            return "";
        }
    }

    public GeneriResponse updateById(GenerePojo gp) {
        GenereEntity genereEntity = new GenereEntity();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        String descr = validDescrizione(gp.getDescrizione());


        if (gp.getId() != null  && gp.getDescrizione() != null && !gp.getDescrizione().isEmpty()) {
            try {
                if(genereService.findGenereById(gp.getId()) != null && !descr.equals(gp.getDescrizione())) {
                    genereEntity = new GenereEntity(gp.getId(), gp.getDescrizione());
                    genereService.save(genereEntity);
                    errorResponse.setStatus(HttpStatus.NO_CONTENT);
                } else {
                    errorResponse.setStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMessaggio(NOTFOUND);
                }
            } catch (Exception e) {
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMessaggio(ERROR);
            }
        } else {
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(INVALID);
        }
        return new GeneriResponse(genereEntity, errorResponse, null);
    }
}




