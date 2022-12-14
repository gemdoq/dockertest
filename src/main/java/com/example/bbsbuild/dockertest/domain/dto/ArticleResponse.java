package com.example.bbsbuild.dockertest.domain.dto;

import com.example.bbsbuild.dockertest.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public static ArticleResponse of(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }
}