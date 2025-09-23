package com.example.post_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.post_project.dto.ArticleDto;
import com.example.post_project.mapper.ArticleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 빈 컨테이너 검색 후 레퍼런스 전달하여 의존성 주입
// 원래는 인터페이스로 만드나, 코드가 너무 간단하여 클래스로 만듦
public class ArticleService {
    // field
    private final ArticleMapper articleMapper;

    // 게시글 등록
    public int createArticle(ArticleDto article) {
        articleMapper.insertArticle(article);
        return article.getId();
    }

    // 게시글 목록 조회
    public List<ArticleDto> retrieveArticleList() {
        return articleMapper.selectArticleList();
    }
    
}
