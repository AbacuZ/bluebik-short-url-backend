package com.bluebik.shorturl.exception;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.bluebik.shorturl.domain.ErrorResponse;

@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<String> list = Arrays.asList(ex.getMessage());
        ErrorResponse<List<String>> response = new ErrorResponse<>();
        response.setMessage(list);
        response.setStatus(status.getReasonPhrase());
        response.setCode(status.value());
        return new ResponseEntity(response, status);
    }

    @ExceptionHandler(URLValidException.class)
    public ResponseEntity handleURLValidException(URLValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> list = Arrays.asList(ex.getMessage());
        ErrorResponse<List<String>> response = new ErrorResponse<>();
        response.setMessage(list);
        response.setStatus(status.getReasonPhrase());
        response.setCode(status.value());
        return new ResponseEntity(response, status);
    }

}
