package com.example.post_project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.post_project.dto.ArticleDto;
import com.example.post_project.dto.ArticleFileDto;
import com.example.post_project.dto.Criteria;
import com.example.post_project.exception.ArticleNotFoundException;
import com.example.post_project.mapper.ArticleFileMapper;
import com.example.post_project.mapper.ArticleMapper;
import com.example.post_project.util.FileUploadUtils;

import lombok.RequiredArgsConstructor;

@Service // (스테레오타입 애너테이션) 비즈니스 로직을 처리하는 클래스를 정의하는데 사용
@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW) // 트랜잭션 설정: 기본값은 readOnly = true
@RequiredArgsConstructor // Bean 컨테이너 검색 후 레퍼런스 전달하여 의존성 주입
// 원래는 인터페이스로 만드나, 코드가 너무 간단하여 클래스로 만듦
public class ArticleService {
    // field
    private final ArticleMapper articleMapper;
    private final ArticleFileMapper articleFileMapper;
    private final FileUploadUtils fileUploadUtils;

    // 게시글 등록
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    // public int createArticle(ArticleDto article, List<MultipartFile> files) {
    //     articleMapper.insertArticle(article);
    //     int articleId = article.getId();

    //     List<ArticleFileDto> articleFiles = fileUploadUtils.uploadFiles(files);

    //     if (articleFiles != null) {
    //     articleFiles.forEach(file -> file.setArticleId(articleId));
    //     articleFileMapper.insertArticleFile(articleFiles);
    //     }
        
    //     return articleId;
    // }
    public int createArticle(ArticleDto article, List<MultipartFile> files) {
        articleMapper.insertArticle(article);
        int articleId = article.getId();

        List<ArticleFileDto> articleFiles = fileUploadUtils.uploadFiles(files);

        if (articleFiles != null) {
            articleFiles.forEach(file -> file.setArticleId(articleId));
            articleFileMapper.insertArticleFile(articleFiles);
        }

        return articleId;
    }

    // 게시글 검색
    public List<ArticleDto> findArticleList(Criteria criteria) {
        return articleMapper.findArticleList(criteria);
    }

    // 게시글 삭제
    @Transactional(readOnly = false)
    public void removeArticle(int id) {
        ArticleDto article = articleMapper.selectArticleById(id);
        if (article == null) {
            throw new ArticleNotFoundException(id + "번 게시글을 찾을 수 없습니다.");
        }
        articleMapper.deleteArticle(id);
    }

    // 게시글 수정
    @Transactional(readOnly = false)
    public void modifyArticleDto(ArticleDto article) {
        articleMapper.updateArticle(article);
    }
    
    // 게시글 상세 조회
    public ArticleDto retrieveArticle(int id) {
        ArticleDto article = articleMapper.selectArticleById(id);
        if (article == null) {
            throw new ArticleNotFoundException();
        }
        return article;
    }

    // 게시글 등록
    @Transactional(readOnly = false)
    public int createArticle(ArticleDto article) {
        articleMapper.insertArticle(article);
        return article.getId();
    }

    // 게시글 목록 조회
    public List<ArticleDto> retrieveArticleList() {
        return articleMapper.selectArticleList();
    }
    
}
