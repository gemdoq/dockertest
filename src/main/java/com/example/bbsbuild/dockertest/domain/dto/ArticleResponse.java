package com.example.bbsbuild.dockertest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
}