package com.myspring.controller;

import com.myspring.dto.BookReqDTO;
import com.myspring.dto.BookResDTO;
import com.myspring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookResDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping
    public List<BookResDTO> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public BookResDTO addBook(@RequestBody BookReqDTO bookReqDTO) {
        return bookService.addBook(bookReqDTO);
    }

    @PutMapping("/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO) {
        return bookService.updateBook(id, bookReqDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + " 삭제처리 되었습니다.");
    }
}

