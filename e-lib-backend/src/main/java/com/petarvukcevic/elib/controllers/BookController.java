package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.dto.command.BookCommand;
import com.petarvukcevic.elib.dto.command.BookUpdateCommand;
import com.petarvukcevic.elib.dto.query.BookQuery;
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
    public ResponseEntity<List<BookQuery>> all() {
        List<BookQuery> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookQuery> findById(@PathVariable("id") Integer id) {
        BookQuery bookQuery = bookService.findById(id);

        return bookQuery != null
                ? new ResponseEntity<>(bookQuery, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping(value = "/category/{id}")
//    public ResponseEntity<Page<Book>> searchByCategoryId(@PathVariable(value = "id") Integer id, Pageable pageable)
//    {
//        Page<Book> books = bookService.findByCategoryId(id, pageable);
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

    @GetMapping(value = "search")
    public ResponseEntity<Page<BookQuery>> searchByTitleOrAuthor(@RequestParam(value = "term") String term,
                                                           Pageable pageable)
    {
        Page<BookQuery> books = bookService.searchByTitleOrAuthor(term, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add-new")
    public ResponseEntity<Void> create(@RequestBody BookCommand bookCommand)
    {
        bookService.create(bookCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody BookUpdateCommand bookUpdateCommand) {
        if (bookUpdateCommand.getId() == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.update(bookUpdateCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
