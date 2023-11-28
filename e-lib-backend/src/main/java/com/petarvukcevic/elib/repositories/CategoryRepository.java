package com.petarvukcevic.elib.repositories;

import com.petarvukcevic.elib.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
