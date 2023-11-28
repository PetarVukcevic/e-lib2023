package com.petarvukcevic.elib.errors.validators;

import com.petarvukcevic.elib.dto.command.CategoryCommand;
import com.petarvukcevic.elib.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class CategoryValidator implements Validator {

    private final CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CategoryCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        CategoryCommand categoryCommand = (CategoryCommand) target;
        validateName(categoryCommand.getName(), errors);
//        validateCategory(categoryCommand.getCategory(), errors);
    }

    private void validateName(String name, Errors errors)
    {
        boolean categoryExists = categoryService.existsByName(name);
        // SELECT count(*) > 0 FROM category WHERE name = ?

        if(categoryExists) {
            errors.rejectValue(
                    "name",
                    "name.already-exists", // translations...
                    "Category with name " + name + " already exists!");
        }
    }
}
