package com.petarvukcevic.elib.repositories;

import com.petarvukcevic.elib.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends JpaRepository<Book, Integer> {
}
