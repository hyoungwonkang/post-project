package com.example.post_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.post_project.dto.ArticleDto;
import com.example.post_project.service.ArticleService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



// @ResponseBody + @Controller
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor // 빈 컨테이너 검색 후 레퍼런스 전달하여 의존성 주입
public class ArticleController {
    // field
    private final ArticleService articleService;

    @PostMapping("/articles")
    public ResponseEntity<Map<String, Integer>> postArticle(@RequestBody ArticleDto article) {
        int id = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", id));
    }
    

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getArticles() {

        List<ArticleDto> articles = articleService.retrieveArticleList();

        return ResponseEntity.ok().body(articles); // builder pattern
        // return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    
}
