package com.myspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

// 기본 생성자 및 Getter, Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReqFormDTO {
    private Long id;

    @NotEmpty(message = "Title은 필수 입력 항목입니다.")
    private String title;

    @NotBlank(message = "Author은 필수 입력 항목입니다")
    private String author;

    @NotBlank(message = "Isbn은 필수 입력 항목입니다")
    private String isbn;

    @NotBlank(message = "Genre은 필수 입력 항목입니다")
    private String genre;
}
