package com.petarvukcevic.elib.mappers;

import com.petarvukcevic.elib.dto.command.CategoryCommand;
import com.petarvukcevic.elib.dto.command.CategoryUpdateCommand;
import com.petarvukcevic.elib.dto.query.CategoryQuery;
import com.petarvukcevic.elib.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper
{
    CategoryQuery toCategoryQuery(Category category);
    Category toCategory(CategoryCommand categoryCommand);
    Category toCategory(CategoryUpdateCommand categoryUpdateCommand);

}
