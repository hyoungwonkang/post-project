package com.example.post_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.post_project.dto.ArticleDto;
import com.example.post_project.dto.Criteria;
import com.example.post_project.service.ArticleService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;





// @ResponseBody + @Controller
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor // Bean 컨테이너 검색 후 레퍼런스 전달하여 의존성 주입
public class ArticleController {
    // field
    private final ArticleService articleService;

    // 게시글 등록2
    @PostMapping("/articles")
    public ResponseEntity<Map<String, Integer>> postArticle(
        @RequestPart(value = "article") ArticleDto article,
        @RequestPart(value = "files") List<MultipartFile> files) {
        
        int id = articleService.createArticle(article, files);
        return ResponseEntity.ok().body(Map.of("id", id));
    }

    // 검색 조건에 해당하는 게시글 목록 조회
    // /api/v1/articles?keyfield=writer&keyword=Alice
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getArticle(
        @RequestParam(value = "keyfield", required = false, defaultValue = "") String keyfield,
        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {

        System.out.println("keyfield: " + keyfield + ", keyword: " + keyword);

        List<ArticleDto> articles = articleService.findArticleList(new Criteria(keyfield, keyword));
        return ResponseEntity.ok().body(articles);
    }
    

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable(value = "id") int id) {
        articleService.removeArticle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }


    @PutMapping("/articles/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable(value = "id") int id, @RequestBody ArticleDto article) {
        
        article.setId(id);
        articleService.modifyArticleDto(article);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }


    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable(value = "id") int id){
        ArticleDto article = articleService.retrieveArticle(id);

        return ResponseEntity.ok().body(article);
    }
    
    // // 게시글 등록
    // @PostMapping("/articles")
    // public ResponseEntity<Map<String, Integer>> postArticle(@RequestBody ArticleDto article) {
    //     int id = articleService.createArticle(article);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", id));
    // }
    

    // @GetMapping("/articles")
    // public ResponseEntity<List<ArticleDto>> getArticles() {

    //     List<ArticleDto> articles = articleService.retrieveArticleList();

    //     return ResponseEntity.ok().body(articles); // builder pattern
    //     // return new ResponseEntity<>(articles, HttpStatus.OK);
    // }
    
}
