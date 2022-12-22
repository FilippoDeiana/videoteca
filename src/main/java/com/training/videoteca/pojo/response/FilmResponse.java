package com.training.videoteca.pojo.response;


import com.training.videoteca.dto.FilmDTO;
import com.training.videoteca.dto.FilmGenereDTO;
import com.training.videoteca.dto.FilmIdDTO;
import com.training.videoteca.dto.FilmInterpreteDTO;
import com.training.videoteca.entitity.FilmEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.FilmPojo.FilmAddInterpretePojo;
import com.training.videoteca.pojo.FilmPojo.FilmDeletePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponse {

    private List<Long> listIdDto;
    private FilmDeletePojo filmDeletePojo;
    private List<FilmInterpreteDTO> listInterpreteDTO;
    private FilmDTO filmDTO;
    private List<FilmGenereDTO> listGenereDTO;
    private List<FilmDTO>  listDTO;
    private FilmEntity filmEntity;
    private ErrorResponse errorResponse;
    private List<FilmEntity> list;


    public FilmResponse(ErrorResponse errorResponse, List<Long> listIdDto, FilmDTO filmDTO) {
        this.listIdDto = listIdDto;
        this.errorResponse = errorResponse;
        this.filmDTO = filmDTO;
    }

    public FilmResponse(FilmDTO filmDTO, List<FilmDTO> listDTO, ErrorResponse errorResponse) {
        this.filmDTO = filmDTO;
        this.listDTO = listDTO;
        this.errorResponse = errorResponse;
    }

    public FilmResponse(List<FilmGenereDTO> listGenereDTO, FilmEntity filmEntity, ErrorResponse errorResponse) {
        this.listGenereDTO = listGenereDTO;
        this.filmEntity = filmEntity;
        this.errorResponse = errorResponse;
    }

    public FilmResponse(List<FilmInterpreteDTO> listInterpreteDTO, ErrorResponse errorResponse) {
        this.listInterpreteDTO = listInterpreteDTO;
        this.errorResponse = errorResponse;
    }

    public FilmResponse(ErrorResponse errorResponse, List<FilmEntity> list) {
        this.errorResponse = errorResponse;
        this.list = list;
    }

    public FilmResponse(FilmEntity filmEntity, ErrorResponse errorResponse) {
        this.filmEntity = filmEntity;
        this.errorResponse = errorResponse;
    }

    public FilmResponse(FilmDeletePojo filmDeletePojo, ErrorResponse errorResponse) {
        this.filmDeletePojo = filmDeletePojo;
        this.errorResponse = errorResponse;
    }
}
