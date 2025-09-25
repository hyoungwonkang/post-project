package com.example.post_project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.post_project.dto.ArticleFileDto;

@Mapper
public interface ArticleFileMapper {
    void insertArticleFile(List<ArticleFileDto> files);
}
