package com.training.videoteca.facade;



import com.training.videoteca.entitity.FilmEntity;
import com.training.videoteca.entitity.SaleEntity;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.SalePojo.SaleCompletoPojo;
import com.training.videoteca.pojo.SalePojo.SaleDeletePojo;
import com.training.videoteca.pojo.SalePojo.SaleIdPojo;
import com.training.videoteca.pojo.response.SaleResponse;
import com.training.videoteca.services.implementation.FilmServiceImpl;
import com.training.videoteca.services.implementation.SaleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class SaleFacade {

    @Autowired
    private SaleServiceImpl saleService;
    @Autowired
    private FilmServiceImpl filmService;

    private static final String NOTPRESENT = "Sala non presente";
    private static final String ERROR = "Errore nella ricerca";
    private static final String DATINOTVALID = "Dati inseriti non validi";

    public SaleResponse securityAll() throws NotFoundException, InternalServerErrorException {
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        List<SaleEntity> lista;
        try {
            if (saleService.findAll() != null && !saleService.findAll().isEmpty()) {
                lista = saleService.findAll();
            } else
                throw new NotFoundException("Non ci sono sale presenti");
        } catch (NotFoundException e) {
            throw new NotFoundException("Non ci sono sale presenti");
        } catch (Exception e) {
            throw new InternalServerErrorException(ERROR);
        }

        return new SaleResponse(lista, er);
    }

    public SaleResponse securityId(String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        SaleEntity s;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (id != null && !id.isEmpty()) {
            try {
                s = saleService.findSaleById(Long.valueOf(id));
                if (s == null) {
                    throw new NotValidException(NOTPRESENT);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTPRESENT);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new SaleResponse(s, er);

    }

    public SaleResponse save(SaleCompletoPojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        SaleEntity saleEntity;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");


        FilmEntity film = validFilm(fp.getId_film());


        if (fp.getNome() != 0 && fp.getDescrizione() != null
                && !fp.getDescrizione().isEmpty() && fp.getId_film() != null) {
            try {
                if (saleService.findSaleById(fp.getId()) == null) {
                    saleEntity = new SaleEntity(fp.getNome(), fp.getDescrizione(), film);

                    saleService.save(saleEntity);
                } else {
                    throw new NotValidException("Sala già esistente");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Sala già esistente");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new SaleResponse(saleEntity, er);
    }

    private FilmEntity validFilm(Long id) {
        FilmEntity f = new FilmEntity();
        if (filmService.findTitoloCompletoById(id) != null) {
            f = filmService.findTitoloCompletoById(id);
        }
        return f;
    }


    public SaleResponse deleteById(SaleIdPojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        SaleEntity saleEntity;
        SaleDeletePojo saleDeletePojo;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");


        if (fp.getId() != 0) {
            try {
                saleEntity = saleService.findSaleById(fp.getId());
                if (saleEntity != null) {
                    saleDeletePojo = new SaleDeletePojo(saleEntity.getId(), saleEntity.getNome(), saleEntity.getDescrizione());
                    saleService.deleteById(fp.getId());
                } else {
                    throw new NotValidException(NOTPRESENT);
                }
            } catch (NotValidException e) {
                throw new NotValidException(NOTPRESENT);
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new SaleResponse(saleDeletePojo, er);
    }

    public SaleResponse update(SaleCompletoPojo fp) throws NotValidException, InternalServerErrorException, NotFoundException {
        SaleEntity saleEntity;
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");
        FilmEntity film = validFilm(fp.getId_film());

        if (fp.getId() != 0 && fp.getId() != null && fp.getNome() != 0 && fp.getDescrizione() != null && !fp.getDescrizione().isEmpty()) {
            try {
                if (saleService.findSaleById(fp.getId()) != null) {
                    saleEntity = saleService.findSaleById(fp.getId());

                    saleEntity.setNome(fp.getNome());
                    saleEntity.setDescrizione(fp.getDescrizione());
                    saleEntity.setFilm(film);
                    saleService.save(saleEntity);
                } else {
                    throw new NotValidException("Sala non valida");
                }
            } catch (NotValidException e) {
                throw new NotValidException("Sala non valida");
            } catch (Exception e) {
                throw new InternalServerErrorException(ERROR);
            }
        } else {
            throw new NotFoundException(DATINOTVALID);
        }
        return new SaleResponse(saleEntity, er);
    }

}
