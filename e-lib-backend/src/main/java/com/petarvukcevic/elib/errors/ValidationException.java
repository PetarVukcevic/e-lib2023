package com.petarvukcevic.elib.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends Exception {

    private final Errors errors;

    public ValidationException(Errors errors) {
        super();
        this.errors = errors;
    }
}
