package com.petarvukcevic.elib.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailDTO {

    private final String recipient;
    private final String body;
}
