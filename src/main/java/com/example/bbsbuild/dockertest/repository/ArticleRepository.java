package com.example.bbsbuild.dockertest.repository;

import com.example.bbsbuild.dockertest.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}