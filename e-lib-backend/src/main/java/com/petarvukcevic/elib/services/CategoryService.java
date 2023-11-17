package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.dto.command.CategoryCommand;
import com.petarvukcevic.elib.dto.command.CategoryUpdateCommand;
import com.petarvukcevic.elib.dto.query.CategoryQuery;
import com.petarvukcevic.elib.entities.Category;
import com.petarvukcevic.elib.mappers.CategoryMapper;
import com.petarvukcevic.elib.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryQuery> findAll()
    {
         return categoryRepository.findAll()
                 .stream()
                 .map(categoryMapper::toCategoryQuery)
                 .toList();

    }

//    public Category findOneById(Integer id)
//    {
//        List<Category> categories = findAll();
//        for (Category category : categories)
//        {
//            if (category.getId() == id) {
//                return category;
//            }
//        }
//        return null;
//    }

    public CategoryQuery create(CategoryCommand categoryCommand)
    {
        Category category = categoryMapper.toCategory(categoryCommand);
        categoryRepository.save(category);

        return categoryMapper.toCategoryQuery(category);
    }

    public void update(CategoryUpdateCommand categoryUpdateCommand)
    {
        categoryRepository.save(categoryMapper.toCategory(categoryUpdateCommand));
    }

    public void deleteById(Integer id) { categoryRepository.deleteById(id); }
}
