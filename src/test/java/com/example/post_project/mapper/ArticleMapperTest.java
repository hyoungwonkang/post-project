package com.example.post_project.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import com.example.post_project.dto.ArticleDto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void testInsertArticle() {
        // given
        ArticleDto articleDto = ArticleDto.builder()
            .title("제목 테스트")
            .contents("내용 테스트")
            .writer("작성자 테스트")
            .build();

        // when
        articleMapper.insertArticle(articleDto);

        // then
        assertThat(articleDto.getId()).isEqualTo(20);
    }

    @Test
    void testSelectArticleList() {
        // given

        // when
        List<ArticleDto> articles = articleMapper.selectArticleList();

        // then
        assertThat(articles).hasSize(7);
        assertThat(articles.size()).isEqualTo(7);
    }
}
