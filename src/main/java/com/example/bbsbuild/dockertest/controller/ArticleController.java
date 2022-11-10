package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.dto.ArticleDto;
import com.example.bbsbuild.dockertest.domain.entity.Article;
import com.example.bbsbuild.dockertest.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String newArticle() {
        return "/articles/new";
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId:{}", savedArticle.getId());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
}