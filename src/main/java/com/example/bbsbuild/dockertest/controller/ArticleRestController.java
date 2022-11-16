package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.dto.ArticleRequest;
import com.example.bbsbuild.dockertest.domain.dto.ArticleResponse;
import com.example.bbsbuild.dockertest.domain.dto.ArticleDto;
import com.example.bbsbuild.dockertest.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) { this.articleService = articleService; }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getOneById(@PathVariable Long id) {
        log.info("게시글 조회 id : {}", id);
        return ResponseEntity.ok(articleService.getOneById(id));
    }

    @PostMapping("")
    public ResponseEntity<ArticleResponse> create(@RequestBody ArticleRequest articleRequest) {
        log.info("게시글 title : {}", articleRequest.getTitle());
        return ResponseEntity.ok().body(articleService.create(articleRequest));
    }
}