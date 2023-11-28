package com.petarvukcevic.elib.errors;

import com.petarvukcevic.elib.errors.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request)
    {
        List<ErrorDTO> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getFieldErrors();
        for(FieldError fieldError : fieldErrors)
        {
            ErrorDTO error = new ErrorDTO(
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage()
            );
            errors.add(error);
        }
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Validation failed!");
        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//        {
//            "message": "Validation failed",
//            "errors": [
//                  { "rejectedValue": Object, "message": "", "fieldName": "" }
//            ]
//        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex)
    {
        List<ErrorDTO> errors = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getErrors().getFieldErrors();
        for(FieldError fieldError : fieldErrors)
        {
            ErrorDTO error = new ErrorDTO(
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage()
            );
            errors.add(error);
        }
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Validation failed!");
        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
