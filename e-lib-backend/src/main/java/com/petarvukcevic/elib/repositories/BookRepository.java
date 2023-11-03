package com.petarvukcevic.elib.repositories;

import com.petarvukcevic.elib.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{

    Page<Book> findByCategoryId(Integer id, Pageable pageable);

    Page<Book> findByTitleContainingOrAuthorContaining(String term, String term1, Pageable pageable);
}
