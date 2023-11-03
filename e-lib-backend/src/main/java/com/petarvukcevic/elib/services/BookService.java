package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.entities.Book;
import com.petarvukcevic.elib.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        List<Book> allBooks = findAll();
        for (Book book : allBooks) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> findByCategoryId(Integer id, Pageable pageable)
    { return bookRepository.findByCategoryId(id, pageable); }

    public Page<Book> searchByTitleOrAuthor(String term, Pageable pageable)
    { return bookRepository.findByTitleContainingOrAuthorContaining(term, term, pageable); }
}
