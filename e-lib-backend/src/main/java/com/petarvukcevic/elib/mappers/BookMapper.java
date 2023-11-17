package com.petarvukcevic.elib.mappers;

import com.petarvukcevic.elib.dto.command.BookCommand;
import com.petarvukcevic.elib.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookCommand bookCommand);
}
