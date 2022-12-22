package com.training.videoteca.facade;

import com.training.videoteca.dto.FilmDTO;
import com.training.videoteca.dto.FilmGenereDTO;
import com.training.videoteca.dto.FilmInterpreteDTO;
import com.training.videoteca.entitity.FilmEntity;
import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.entitity.InterpreteEntity;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.FilmPojo.*;
import com.training.videoteca.pojo.response.FilmResponse;
import com.training.videoteca.services.implementation.FilmServiceImpl;
import com.training.videoteca.services.implementation.GenereServiceImpl;
import com.training.videoteca.services.implementation.InterpreteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class FilmFacade {

    @Autowired
    private FilmServiceImpl fs;
    @Autowired
    private GenereServiceImpl gs;
    @Autowired
    private InterpreteServiceImpl is;



    private static final String NOTFOUND = "Film non trovato";
    private static final String ERROR ="Errore nella ricerca";
    private static final String DATINOTVALID ="Dati inseriti non validi";
    public FilmResponse securityAll() {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        return new FilmResponse(null, fs.getListaTitoli(), er);
    }

    public FilmResponse securityAllId() {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        return new FilmResponse(er,fs.getListaTitoliId(),null);
    }

    public FilmResponse securityId(String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmDTO f;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (id != null && !id.isEmpty()) {
            try {
                f = fs.findTitoloById(Long.valueOf(id));
                if (f == null) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(f, null, er);

    }

    public FilmResponse securityByNome(String nome) throws NotValidException, InternalServerErrorException, NotFoundException {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<FilmDTO> list;

        if (nome != null && !nome.isEmpty()) {
            try {
                list = fs.findTitoloByNome(nome);
                if (list == null || list.isEmpty()) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(null, list, er);
    }

    public FilmResponse securityByNomeCompleto(String nome) throws NotValidException, InternalServerErrorException, NotFoundException {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<FilmEntity> list;

        if (nome != null && !nome.isEmpty()) {
            try {
                list = fs.findFilmByNomeCompleto(nome);
                if (list == null || list.isEmpty()) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(er, list);
    }

    public FilmResponse securityByAnno(String anno) throws NotValidException, InternalServerErrorException, NotFoundException {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        List<FilmDTO> list;

        if (anno != null && !anno.isEmpty() && anno.length() == 4) {
            try {
                list = fs.findTitoloByAnno(Integer.valueOf(anno));
                if (list == null || list.isEmpty()) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(null, list, er);
    }

    public FilmResponse securityByGenere(String genere) throws InternalServerErrorException, NotValidException, NotFoundException {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<FilmGenereDTO> list;

        if (genere != null && !genere.isEmpty()) {
            try {
                list = fs.findTitoloByGenere(genere);
                if (list == null || list.isEmpty()) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(list, null, er);

    }

    public FilmResponse securityByInterprete(String nome) throws InternalServerErrorException, NotValidException, NotFoundException {
        List<FilmEntity> list;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (nome != null && !nome.isEmpty()) {
            try {
                list = fs.findInterpreteByNome(nome);
                if (list == null || list.isEmpty()) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse( er,list);

    }


    public FilmResponse securityListaAll() {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        return new FilmResponse(er, fs.findListaAll());
    }

    public FilmResponse securityTitoloCompletoById(String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmEntity f;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (id != null && !id.isEmpty()) {
            try {
                f = fs.findTitoloCompletoById(Long.valueOf(id));
                if (f == null) {
                    throw new NotValidException(NOTFOUND);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTFOUND);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(f, er);


    }//da finire

    public FilmResponse save(FilmSavePojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {

        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "Aggiunta film avvenuta con successo");
        FilmEntity f;
        GenereEntity genere = validGenere(fp.getId_genere());
        Set<InterpreteEntity> interpreti = validInterpreti(fp.getId_interprete());


        String titolo = validTitolo(fp.getTitolo());


        if (fp.getTitolo() != null && !fp.getTitolo().isEmpty() && fp.getAnno() != null && !fp.getAnno().isEmpty()
                && fp.getAnno().length() == 4 && Integer.valueOf(fp.getAnno()) > 1800 && Integer.valueOf(fp.getAnno()) <= 2023) {
            try {
                if (!titolo.equals(fp.getTitolo())) {
                     f = new FilmEntity(fp.getId(), fp.getTitolo(), Integer.valueOf(fp.getAnno()), genere, interpreti);
                    fs.save(f);
                } else {
                    throw new NotValidException("Titolo già esistente");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Titolo già esistente");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(f, er);
    }

    private Set<InterpreteEntity> validInterpreti(Set<Long> id) {
        Set<InterpreteEntity> interprete = new HashSet<>();

        for (Long i : id) {
            if (is.findInterpreteById(i) != null) {
                interprete.add(is.findInterpreteById(i));
            }
        }
        return interprete;
    }

    private GenereEntity validGenere(Long id) {
        GenereEntity g = new GenereEntity();
        if (gs.findGenereById(id) != null) {
            g = gs.findGenereById(id);
        }
        return g;
    }

    private String validTitolo(String titolo) {
        if (fs.findFilmByNome(titolo) != null) {
            return titolo;
        } else {
            return "";
        }
    }

    public FilmResponse update(FilmUpdatePojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmEntity fe;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        String titolo = validTitolo(fp.getTitolo());

        if (fp.getTitolo() != null && !fp.getTitolo().isEmpty() && !fp.getAnno().isEmpty()
                && fp.getAnno().length() == 4 && Integer.valueOf(fp.getAnno()) > 1800 && Integer.valueOf(fp.getAnno()) <= 2023) {
            try {
                if (fs.findTitoloCompletoById(fp.getId()) != null && !titolo.equals(fp.getTitolo())) {
                    fe = fs.findTitoloCompletoById(fp.getId());
                    fe.setId(fp.getId());
                    fe.setTitolo(fp.getTitolo());
                    fe.setAnno(Integer.valueOf(fp.getAnno()));

                    fs.save(fe);
                } else {
                    throw new NotValidException("Titolo già esistente");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Titolo già esistente");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(fe, er);
    }

    public FilmResponse updateGenere(FilmGenereUpdatePojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmEntity fe;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        log.info(fp.toString());

        if (fp.getId_film() != null && fp.getId_film() != 0  && fp.getId_genere() != null &&  fp.getId_genere() != 0) {
            try {
                if (fs.findTitoloCompletoById(fp.getId_film()) != null && gs.findGenereById(fp.getId_genere()) != null) {
                    fe = fs.findTitoloCompletoById(fp.getId_film());
                    fe.setGenere(gs.findGenereById(fp.getId_genere()));

                    fs.save(fe);
                } else {
                    throw new NotValidException("Titolo o genere non esistenti");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Titolo o genere non esistenti");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new FilmResponse(fe, er);

    }

    public FilmResponse deleteFilm(FilmIdPojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmEntity fe;
        FilmDeletePojo delete;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "Rimozione avvenuta con successo");

        if (fp.getId() != null && !fp.getId().isEmpty()) {
            try {
                if (fs.findTitoloCompletoById(Long.valueOf(fp.getId())) != null) {
                    fe = fs.findTitoloCompletoById(Long.valueOf(fp.getId()));
                    delete = new FilmDeletePojo(fe.getId(), fe.getTitolo(), fe.getAnno());
                    fs.deleteById(Long.valueOf(fp.getId()));
                } else {
                    throw new NotValidException("Film non esistente");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Id non esistente");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException("Titolo invalido");
        }
        return new FilmResponse(delete, er);

    }

    public FilmResponse addInterprete(FilmAddInterpretePojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmEntity fe;
        Set<InterpreteEntity> interpreti = validInterpretiId(fp.getId_interprete());
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (fp.getId_film() != null && !fp.getId_film().isEmpty()
                && fp.getId_interprete() != null && !fp.getId_interprete().isEmpty()) {
            try {
                if (fs.findTitoloCompletoById(Long.valueOf(fp.getId_film())) != null) {

                    fe = fs.findTitoloCompletoById(Long.valueOf(fp.getId_film()));
                    for (InterpreteEntity i: interpreti) {
                        fe.getInterprete().add(i);
                    }

                    fs.save(fe);

                } else {
                    throw new NotValidException("Film o Interprete non presente");
                }
            } catch (NotValidException e) {
                throw new NotValidException(e.getMessaggio());
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        log.info(fe.toString());
        return new FilmResponse(fe, er);

    }

    private Set<InterpreteEntity> validInterpretiId(Set<Long> id_interprete) {

        Set<InterpreteEntity> interprete = new HashSet<>();

        for (Long i : id_interprete) {
            if (is.findInterpreteById(i) != null) {
                interprete.add(is.findInterpreteById(i));
            }
        }
        return interprete;
    }

    public FilmResponse removeInterprete(FilmInterpretePojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmEntity fe;

        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (fp.getId_film() != null && !fp.getId_film().isEmpty()
                && fp.getId_interprete() != null && !fp.getId_interprete().isEmpty()) {
            try {
                if (fs.findTitoloCompletoById(Long.valueOf(fp.getId_film())) != null
                        && is.findInterpreteById(Long.valueOf(fp.getId_interprete())) != null) {
                    fe = fs.findTitoloCompletoById(Long.valueOf(fp.getId_film()));

                    fe.getInterprete().remove(is.findInterpreteById(Long.valueOf(fp.getId_interprete())));

                    fs.save(fe);

                } else {
                    throw new NotValidException("Film o Interprete non presente");
                }
            } catch (NotValidException e) {
                throw new NotValidException(e.getMessaggio());
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        log.info(fe.toString());
        return new FilmResponse(fe, er);

    }
}

