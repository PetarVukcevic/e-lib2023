package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.dto.command.BookCommand;
import com.petarvukcevic.elib.dto.command.BookUpdateCommand;
import com.petarvukcevic.elib.dto.query.BookQuery;
import com.petarvukcevic.elib.entities.Book;
import com.petarvukcevic.elib.mappers.BookMapper;
import com.petarvukcevic.elib.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public void create(BookCommand bookCommand)
    {
//        Book book = bookMapper.toBook(bookCommand);
//        bookRepository.save(book);

        bookRepository.save(bookMapper.toBook(bookCommand));
    }

    public List<BookQuery> findAll()
    {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookQuery)
                .toList();
    }

    public BookQuery findById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        // Check if the book with the given ID exists
        return optionalBook.map(bookMapper::toBookQuery).orElse(null);
    }

    public void update(BookUpdateCommand bookUpdateCommand) {
        bookRepository.save(bookMapper.toBook(bookUpdateCommand));
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Page<BookQuery> searchByTitleOrAuthor(String term, Pageable pageable)
    {
        return bookRepository.findByTitleContainingOrAuthorContaining(term,term,pageable)
                .map(bookMapper::toBookQuery);
    }

    public Page<BookQuery> findByCategoryId(Integer id, Pageable pageable)
    {
        return bookRepository.findByCategoryId(id, pageable)
                .map(bookMapper::toBookQuery);
    }


    //    Cache start
//    @Cacheable(value = "book", key = "#id")
//    public Book findByIdOrElse(Integer id) {
//        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//    }
//
//    @Cacheable(value = "book-page", key = "'page-' + #pageable.page + '-size' + #pageable.size")
//    public Page<Book> findPage(Pageable pageable)
//    {
//        return bookRepository.findAll(pageable);
//    }
//
//    @Cacheable(value = "books", key = "'all-books'")
//    public List<Book> findAllOrElse()
//    {
//        return bookRepository.findAll(); // select * from books => List<Book> => CACHE!
//    }
//
//    @CachePut(cacheNames = "book", key = "#result.id")
//    public Book save(Book book)
//    {
//        return bookRepository.save(book);
//    }
//
//    @CacheEvict(cacheNames = "book", key = "#result")
//    public void deleteById(Integer id)
//    {
//        bookRepository.deleteById(id);
//    }
//
//    @CacheEvict(cacheNames = {"book","books"},allEntries = true)
//    public void  evictAllEntriesFromCache() {
//        log.warn("All entries from book cache are evicted!");
//    }
//// CACHE END
//    public void addBook(Book book) {
//        bookRepository.save(book);
//    }
//

}
