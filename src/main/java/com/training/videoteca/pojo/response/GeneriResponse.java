package com.training.videoteca.pojo.response;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GeneriResponse {

    private GenereEntity generiEntity;
    private ErrorResponse errorResponse;
    private List<GenereEntity> list;



}
