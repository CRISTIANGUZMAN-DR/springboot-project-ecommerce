package com.cguzman.springboot_project_ecommerce.exceptions;

import com.cguzman.springboot_project_ecommerce.entities.Errors.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ErrorException> badArgument(Exception e){
        ErrorException error =  new ErrorException();
        error.setError("El argumento esperado no es del mismo TIPO del argumento que se recibio");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorException> methodNoSupported(Exception e){
        ErrorException error =  new ErrorException();
        error.setError("El metodo no soporta la url especificada");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> argumentNotValidException(Exception e){
        ErrorException error =  new ErrorException();
        error.setError("Faltan campos obligatorios");
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

}
