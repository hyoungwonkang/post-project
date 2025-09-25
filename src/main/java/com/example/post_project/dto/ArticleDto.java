// persistant layer

package com.example.post_project.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter @ToString
public class ArticleDto {
    // field, property
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime regDate;

    // Article 1 : N ArticleFile
    List<ArticleFileDto> files = new ArrayList<>();
}
