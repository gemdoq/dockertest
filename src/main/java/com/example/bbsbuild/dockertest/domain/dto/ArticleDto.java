package com.example.bbsbuild.dockertest.domain.dto;

import com.example.bbsbuild.dockertest.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(this.id, this.title, this.content);
    }
}