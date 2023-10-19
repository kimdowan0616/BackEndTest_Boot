package com.myspring.controller;

import com.myspring.dto.BookReqDTO;
import com.myspring.dto.BookReqFormDTO;
import com.myspring.dto.BookResDTO;
import com.myspring.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bookspage")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/index")
    public ModelAndView index() {
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        return new ModelAndView("index", "books", bookResDTOList);
    }

    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid BookReqDTO book, BindingResult result, Model model) {
        // 입력 항목 검증 오류가 발생했나요??
        if (result.hasErrors()) {
            return "add-book";
        }
        // 등록 요청
        bookService.addBook(book);

        return "redirect:/bookspage/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResDTO);
        System.out.println(bookResDTO.getId());
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid BookReqFormDTO book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }
        bookService.updateBookForm(book);
        return "update-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        bookService.deleteBook(id);

        return "redirect:/bookspage/index";
    }
}
