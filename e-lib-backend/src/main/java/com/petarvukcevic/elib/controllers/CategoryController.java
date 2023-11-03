package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.entities.Book;
import com.petarvukcevic.elib.entities.Category;
import com.petarvukcevic.elib.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<Category> findOneById(@PathVariable("id") Integer id) {
        Category category = categoryService.findOneById(id);

        return category != null
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add-new")
    public ResponseEntity<Void> addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Void> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        categoryService.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id)
    {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
