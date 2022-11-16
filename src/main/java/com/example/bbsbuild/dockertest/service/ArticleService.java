package com.example.bbsbuild.dockertest.service;

import com.example.bbsbuild.dockertest.domain.dto.ArticleResponse;
import com.example.bbsbuild.dockertest.domain.entity.Article;
import com.example.bbsbuild.dockertest.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    public final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ResponseEntity<ArticleResponse> getArticleById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article = optionalArticle.get();
        ArticleResponse articleResponse = Article.of(article);
        return ResponseEntity.ok().body(articleResponse);
    }
}