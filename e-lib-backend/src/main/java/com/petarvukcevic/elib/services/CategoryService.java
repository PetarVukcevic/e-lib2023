package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.entities.Category;
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

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOneById(Integer id)
    {
        List<Category> categories = findAll();
        for (Category category : categories)
        {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public void addCategory(Category category) { categoryRepository.save(category); }

    public void updateCategory(Category category) { categoryRepository.save(category); }

    public void deleteCategory(Integer id) { categoryRepository.deleteById(id); }
}
