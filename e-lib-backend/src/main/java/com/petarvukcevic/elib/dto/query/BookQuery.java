package com.petarvukcevic.elib.dto.query;

import lombok.Data;

@Data
public class BookQuery {
    private Integer id;
    private String title;
    private String author;
    private String genre;
    private Integer pages;
    private String imageUrl;
    private String slug;
}
