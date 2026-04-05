package com.cguzman.springboot_project_ecommerce.exceptions;

import com.cguzman.springboot_project_ecommerce.entities.Errors.ErrorException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler({RegistryNotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorException> resourceNotFound(Exception e){
        ErrorException error = new ErrorException();
        error.setError("No se encontró el recurso, valide el endpoint por favor");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorException> invalidArgument(Exception e){
        ErrorException error =  new ErrorException();
        error.setError("El argumento esperado no es del mismo TIPO del argumento que se recibio");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorException> unsupportedMethod(Exception e){
        ErrorException error =  new ErrorException();
        error.setError("El metodo no soporta la url especificada");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> requiredFields (Exception e){
        ErrorException error =  new ErrorException();
        error.setError("Faltan campos obligatorios o existen campos incorrectos");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorException> duplicateData(Exception e){
        ErrorException error = new ErrorException();
        error.setError("Campo del request duplicado en la base de datos");
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}
