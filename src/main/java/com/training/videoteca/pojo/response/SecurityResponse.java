package com.training.videoteca.pojo.response;

import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.SecurityPojo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecurityResponse {

    private SecurityPojo securityPojo;
    private ErrorResponse errorResponse;
}
