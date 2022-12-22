package com.training.videoteca.errors.handler;

import com.training.videoteca.errors.*;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.errors.ExternalCommunicationErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotFound(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.NOT_FOUND);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NotValidException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotValid(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.BAD_REQUEST);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ErrorResponse> exceptionForbidden(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.FORBIDDEN);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.FORBIDDEN);


    }

    @ExceptionHandler(InternalServerErrorException.class)
    public final ResponseEntity<ErrorResponse> exceptionInternalServerError(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @ExceptionHandler(ExternalCommunicationErrorException.class)
    public final ResponseEntity<ErrorResponse> exceptionExternalCommunicationError(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);


    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ErrorResponse> exceptionUnauthorized(Exception e) {

        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.UNAUTHORIZED);
        er.setMessaggio(e.getMessage());

        return new ResponseEntity<>(er, new HttpHeaders(), HttpStatus.UNAUTHORIZED);


    }
}


