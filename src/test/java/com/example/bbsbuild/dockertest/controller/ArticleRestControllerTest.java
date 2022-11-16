package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.dto.ArticleResponse;
import com.example.bbsbuild.dockertest.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("Long id로 JSON이 잘 찾아와지나")
    void find() throws Exception {
        ArticleResponse articleResponse = ArticleResponse.builder()
                .id((long)1)
                .title("1번article의title")
                .content("1번article의content")
                .build();

        given(articleService.getArticleById((long)1)).willReturn(articleResponse);

        long articleId = 1;

        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("1번article의title"))
                .andDo(print());

        verify(articleService).getArticleById(articleId);
    }
}