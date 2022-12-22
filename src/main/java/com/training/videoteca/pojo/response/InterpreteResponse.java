package com.training.videoteca.pojo.response;

import com.training.videoteca.entitity.GenereEntity;
import com.training.videoteca.entitity.InterpreteEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class InterpreteResponse {

    private InterpreteEntity interpreteEntity;
    private ErrorResponse errorResponse;
    private List<InterpreteEntity> list;
}
