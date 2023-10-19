package com.myspring.controller;

import com.myspring.entity.Book;
import com.myspring.exception.BusinessException;
import com.myspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookSimpleRestController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.orElseThrow(() -> new BusinessException(id + " Not Found", HttpStatus.NOT_FOUND));

        return book;
    }

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BusinessException(isbn + " Not Found", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book bookDetail) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " Not Found", HttpStatus.NOT_FOUND));

        book.setTitle(bookDetail.getTitle());
        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok(id + " 삭제 완료");
    }
}
