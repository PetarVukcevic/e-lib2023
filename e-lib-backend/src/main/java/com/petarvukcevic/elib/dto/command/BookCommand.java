package com.petarvukcevic.elib.dto.command;

import com.petarvukcevic.elib.dto.query.CategoryQuery;
import lombok.Data;


@Data
public class BookCommand {

    private String title;
    private String author;
    private String genre;
    private Integer pages;
    private String slug;
    private String imageUrl;
    private CategoryQuery category;
}
