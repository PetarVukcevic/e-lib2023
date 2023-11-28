package com.petarvukcevic.elib.errors.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ErrorDTO {

    private final String fieldName; // attribute name
    private final Object rejectedValue;
    private final String message; // message from validation annotation

}
