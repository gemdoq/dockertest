package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.dto.ArticleDto;
import com.example.bbsbuild.dockertest.domain.entity.Article;
import com.example.bbsbuild.dockertest.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String getSingleArticle(@PathVariable("id") Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            model.addAttribute("article", article);
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            model.addAttribute("article", article);
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article updatedArticle = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", updatedArticle);
        return String.format("redirect:/articles/%d", updatedArticle.getId());
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("message", String.format("id:%d번 글이 지워졌습니다.", id));
        return "articles/show";
    }
}