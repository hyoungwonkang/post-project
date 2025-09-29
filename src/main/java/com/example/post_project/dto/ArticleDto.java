// persistant layer

package com.example.post_project.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    // field, property
    private int id;
    @NotBlank(message = "")
    @Size(max=100, message="Title cannot exceed 100 char")
    private String title;
    @NotBlank(message = "Writer cannot be blank")
    private String writer;
    @NotBlank(message = "Contents cannot be empty")
    private String contents;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    // Article 1 : N ArticleFile
    private List<ArticleFileDto> files = new ArrayList<>();

    // Article 1 : 1 User
    private UserDto user;
}
