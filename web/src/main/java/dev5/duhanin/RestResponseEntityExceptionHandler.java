package dev5.duhanin;

import dev5.duhanin.exceptions.NotFoundException;
import dev5.duhanin.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class, IllegalArgumentException.class, IllegalStateException.class, ServiceException.class})
    protected ResponseEntity handleConflict(RuntimeException ex) {
        return new ResponseEntity<>(Collections.singletonMap("Message error", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
