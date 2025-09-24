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

    // 삭제
    @Test
    void testRemoveArticle() {
        // given
        ArticleDto articleDto = ArticleDto.builder()
            .title("제목 테스트")
            .contents("내용 테스트")
            .writer("작성자 테스트")
            .build();

        articleMapper.insertArticle(articleDto);

        // when
        articleMapper.deleteArticle(articleDto.getId());
        ArticleDto foundArticle = articleMapper.selectArticleById(articleDto.getId());

        // then
        assertThat(foundArticle).isNull();
    }

    // 수정
    @Test
    void testUpdateArticle() {
        // given
        ArticleDto articleDto = ArticleDto.builder()
            .title("제목 테스트")
            .contents("내용 테스트")
            .writer("작성자 테스트")
            .build();

        articleMapper.insertArticle(articleDto);

        ArticleDto articleDto1 = ArticleDto.builder()
            .id(articleDto.getId())
            .title("제목 수정 테스트")
            .contents("내용 수정 테스트")
            .writer("작성자 수정 테스트")
            .build();

        // when
        articleMapper.updateArticle(articleDto1);
        ArticleDto updatedArticle = articleMapper.selectArticleById(articleDto.getId());

        // then
        assertThat(updatedArticle.getTitle()).isEqualTo(articleDto1.getTitle());
        assertThat(updatedArticle.getContents()).isEqualTo(articleDto1.getContents());
    }

    // 상세 조회
    @Test
    void testSelectArticleById() {
        // given
        ArticleDto articleDto = ArticleDto.builder()
            .title("제목 테스트1")
            .contents("내용 테스트1")
            .writer("작성자 테스트1")
            .build();
        articleMapper.insertArticle(articleDto);

        int id = articleDto.getId();

        // when
        articleMapper.selectArticleById(id);

        // then
        assertThat(articleDto.getContents()).isNotNull();

    }

    // 등록
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

    // 전체 조회
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
