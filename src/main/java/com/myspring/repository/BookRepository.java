package com.myspring.repository;

import com.myspring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);

    List<Book> findById(String id);

    Optional<Book> findByIsbn(String isbn);
}
