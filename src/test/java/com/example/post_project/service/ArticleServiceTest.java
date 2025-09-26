package com.example.post_project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.post_project.dto.ArticleDto;
import com.example.post_project.exception.ArticleNotFoundException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    
    @Test
    void testRetrieveArticle() {

        // given
        int id = 12;

        // when
        ArticleDto articleDto = articleService.retrieveArticle(id);
        
        System.out.println(articleDto.toString());

        if(articleDto.getUser() != null) {
            System.out.println("User: " + articleDto.getUser());
        }
        
        if(articleDto.getFiles().isEmpty()) {
            articleDto.getFiles().forEach(System.out::println);
        }

        // then
        assertThat(articleDto).isNotNull();
    }

     @Test
    void testRetrieveArticle1() {

        // given
        int id = 12;

        // when
        assertThatThrownBy(() -> {
            ArticleDto articleDto = articleService.retrieveArticle(id);
        }).isInstanceOf(ArticleNotFoundException.class)
            .hasMessage(id + "번 게시글을 찾을 수 없습니다.");
    }
}
