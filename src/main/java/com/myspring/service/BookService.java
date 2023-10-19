package com.myspring.service;

import com.myspring.dto.BookReqDTO;
import com.myspring.dto.BookReqFormDTO;
import com.myspring.dto.BookResDTO;
import com.myspring.entity.Book;
import com.myspring.exception.BusinessException;
import com.myspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(book, BookResDTO.class);

        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public BookResDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn + " Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(book, BookResDTO.class);

        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public List<BookResDTO> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDTO> bookResDTOList = bookList.stream()
                .map(book -> modelMapper.map(book, BookResDTO.class))
                .collect(Collectors.toList());

        return bookResDTOList;
    }

    public BookResDTO addBook(BookReqDTO bookReqDTO) {
        Book book = modelMapper.map(bookReqDTO, Book.class);
        Book savedBook = bookRepository.save(book);

        return modelMapper.map(savedBook, BookResDTO.class);
    }

    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqDTO.getTitle());

        return modelMapper.map(existBook, BookResDTO.class);
    }

    public void updateBookForm(BookReqFormDTO bookReqFormDTO) {
        Book existBook = bookRepository.findById(bookReqFormDTO.getId())
                .orElseThrow(() -> new BusinessException(bookReqFormDTO.getId() + " Not Found", HttpStatus.NOT_FOUND));

        existBook.setTitle(bookReqFormDTO.getTitle());
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " Not Found", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
    }
}
