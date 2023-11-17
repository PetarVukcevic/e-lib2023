package com.petarvukcevic.elib.dto.command;

import lombok.Data;

@Data
public class CategoryUpdateCommand {

    private Integer id;
    private String name;
    private String description;
}
