package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.dto.command.BookCommand;
import com.petarvukcevic.elib.dto.command.BookUpdateCommand;
import com.petarvukcevic.elib.dto.query.BookQuery;
import com.petarvukcevic.elib.entities.Book;
import com.petarvukcevic.elib.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.util.List;

@Slf4j
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

    @GetMapping("/{slug}")
    public ResponseEntity<BookQuery> findBySlug(@PathVariable("slug") String slug) {
        BookQuery bookQuery = bookService.findBySlug(slug);

        return bookQuery != null
                ? new ResponseEntity<>(bookQuery, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<Page<BookQuery>> searchByCategoryId(@PathVariable(value = "id") Integer id, Pageable pageable)
    {
        Page<BookQuery> books = bookService.findByCategoryId(id, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "search")
    public ResponseEntity<Page<BookQuery>> searchByTitleOrAuthor(@RequestParam(value = "term") String term,
                                                           Pageable pageable)
    {
        Page<BookQuery> books = bookService.searchByTitleOrAuthor(term, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add-new")
    public ResponseEntity<Void> create(@RequestBody BookCommand bookCommand) {
        String slug = makeSlug(bookCommand.getTitle());
        log.info("Slug: " + slug);
        bookCommand.setSlug(slug);  // Assuming you have a setSlug method in your BookCommand class
        bookService.create(bookCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String makeSlug(String title) {
        // Convert to lowercase and replace spaces with underscores
        String slug = title.toLowerCase().replaceAll("\\s", "_");

        // You can further sanitize the slug if needed, for example, removing special characters

        // URL encode the slug to handle special characters properly
        return UriUtils.encodePath(slug, "UTF-8");
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
