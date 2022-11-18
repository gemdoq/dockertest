package com.example.bbsbuild.dockertest.service;

import com.example.bbsbuild.dockertest.domain.dto.ArticleRequest;
import com.example.bbsbuild.dockertest.domain.dto.ArticleResponse;
import com.example.bbsbuild.dockertest.domain.entity.Article;
import com.example.bbsbuild.dockertest.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    public final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getOneById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        return ArticleResponse.of(article);
    }

    public ArticleResponse create(ArticleRequest articleRequest) {
        return ArticleResponse.of(articleRepository.save(articleRequest.toEntity()));
    }
}