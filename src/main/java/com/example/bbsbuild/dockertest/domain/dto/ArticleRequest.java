package com.example.bbsbuild.dockertest.domain.dto;

import com.example.bbsbuild.dockertest.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}


