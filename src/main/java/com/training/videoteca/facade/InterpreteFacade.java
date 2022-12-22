package com.training.videoteca.facade;


import com.training.videoteca.entitity.InterpreteEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.InterpretePojo;
import com.training.videoteca.pojo.response.InterpreteResponse;
import com.training.videoteca.services.implementation.InterpreteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InterpreteFacade {
    @Autowired
    private InterpreteServiceImpl is;

    private static final String NOTFOUND = "Interprete non trovato";
    private static final String ERROR = "Errore nella ricerca";
    private static final String NOTVALID = "Dati invalidi";

    public InterpreteResponse securityId(String id) {
        InterpreteEntity interpreteEntity = new InterpreteEntity();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (id != null && !id.isEmpty()) {
            try {
                interpreteEntity = is.findInterpreteById(Long.valueOf(id));
                if (interpreteEntity == null) {
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
            errorResponse.setMessaggio(NOTFOUND);
        }
        return new InterpreteResponse(interpreteEntity, errorResponse, null);
    }


    public InterpreteResponse securityAll() {
        List<InterpreteEntity> interpreteEntities = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        try {
            interpreteEntities = is.findAll();
            if (interpreteEntities != null && !interpreteEntities.isEmpty()) {
                errorResponse.setStatus(HttpStatus.OK);
            } else {
                errorResponse.setStatus(HttpStatus.NOT_FOUND);
                errorResponse.setMessaggio("Non ci sono interpreti");
            }
        } catch (Exception e) {
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            errorResponse.setMessaggio(ERROR);
        }
        return new InterpreteResponse(null, errorResponse, interpreteEntities);
    }

    public InterpreteResponse securityByNomeLike(String nome) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<InterpreteEntity> list = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            try {
                list = is.findByNome(nome);
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
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(NOTVALID);
        }
        return new InterpreteResponse(null, errorResponse, list);
    }

    public InterpreteResponse securityByCognomeLike(String cognome) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<InterpreteEntity> list = new ArrayList<>();

        if (cognome != null && !cognome.isEmpty()) {
            try {
                list = is.findByCognome(cognome);
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
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(NOTVALID);
        }
        return new InterpreteResponse(null, errorResponse, list);
    }

    public InterpreteResponse securityByFullname(String fullname) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<InterpreteEntity> list = new ArrayList<>();

        if (fullname != null && !fullname.isEmpty()) {
            try {
                list = is.findByFullName(fullname);
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
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(NOTVALID);
        }
        return new InterpreteResponse(null, errorResponse, list);
    }


    /**
     * save()
     * ho lasciato che vengano messi duplicati per omonimia
     */

    public InterpreteResponse save(InterpretePojo ip) {
        InterpreteEntity interpreteEntity = new InterpreteEntity();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        String nome = validNome(interpreteEntity.getNome());
        String cognome = validCognome(interpreteEntity.getCognome());

        if (ip.getNome() != null && !ip.getNome().isEmpty() &&
                ip.getCognome() != null && !ip.getCognome().isEmpty()) {
            try {
                if (nome.equals(interpreteEntity.getNome()) && cognome.equals(interpreteEntity.getCognome())) {
                    errorResponse.setStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMessaggio("Interprete già presente");

                } else {
                    interpreteEntity = new InterpreteEntity(ip.getId(), ip.getNome(), ip.getCognome());
                    is.save(interpreteEntity);
                    errorResponse.setStatus(HttpStatus.NO_CONTENT);

                }

            } catch (Exception e) {
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMessaggio(ERROR);

            }
        } else {
            errorResponse.setStatus(HttpStatus.NOT_FOUND);
            errorResponse.setMessaggio(NOTVALID);
        }
        return new InterpreteResponse(interpreteEntity, errorResponse, null);

    }

    private String validCognome(String cognome) {
        if (is.findInterpreteByCognomeIgnoreCase(cognome) != null) {
            return cognome;
        } else {
            return "";
        }
    }

    private String validNome(String nome) {

        if (is.findInterpreteByNomeIgnoreCase(nome) != null) {
            return nome;
        } else {
            return "";
        }
    }

    /**
     * update()
     * ho lasciato che vengano messi duplicati per omonimia
     */
    public InterpreteResponse updateById(InterpretePojo ip) {
        InterpreteEntity interpreteEntity = new InterpreteEntity();
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (ip.getId() != null && ip.getNome() != null && !ip.getNome().isEmpty() &&
                ip.getCognome() != null && !ip.getCognome().isEmpty()) {
            try {
                if (is.findInterpreteById(ip.getId()) != null) {
                    interpreteEntity = new InterpreteEntity(ip.getId(), ip.getNome(), ip.getCognome());
                    is.save(interpreteEntity);
                    errorResponse.setStatus(HttpStatus.OK);
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
            errorResponse.setMessaggio(NOTVALID);
        }
        return new InterpreteResponse(interpreteEntity, errorResponse, null);
    }
}
