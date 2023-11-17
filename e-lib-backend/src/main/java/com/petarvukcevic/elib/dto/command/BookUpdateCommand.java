package com.petarvukcevic.elib.dto.command;

import com.petarvukcevic.elib.dto.query.CategoryQuery;
import lombok.Data;

@Data
public class BookUpdateCommand {
    private Integer id;
    private String title;
    private String author;
    private String genre;
    private Integer pages;
    private CategoryQuery category;
}
