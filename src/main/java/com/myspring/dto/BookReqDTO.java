package com.myspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookReqDTO {
    private Long id;

    @NotEmpty(message = "title은 필수 항목입니다")
    private String title;

    @NotBlank(message = "Author은 필수 입력 항목입니다")
    private String author;

    @NotBlank(message = "Isbn은 필수 입력 항목입니다")
    private String isbn;

    @NotBlank(message = "Genre은 필수 입력 항목입니다")
    private String genre;
}
