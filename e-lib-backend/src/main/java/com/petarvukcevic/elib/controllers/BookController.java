package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.entities.Book;
import com.petarvukcevic.elib.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> all() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable int id)
    {
        Book book = bookService.findById(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Page<Book>> searchByCategoryId(@PathVariable(value = "id") Integer id, Pageable pageable)
    {
        Page<Book> books = bookService.findByCategoryId(id, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "search")
    public ResponseEntity<Page<Book>> searchByTitleOrAuthor(@RequestParam(value = "term") String term,
                                                           Pageable pageable)
    {
        Page<Book> books = bookService.searchByTitleOrAuthor(term, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add-new")
    public ResponseEntity<Void> addBook(@RequestBody Book book)
    {
        bookService.addBook(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Void> updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
