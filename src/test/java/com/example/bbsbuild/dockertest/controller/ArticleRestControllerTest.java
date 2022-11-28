package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.dto.ArticleRequest;
import com.example.bbsbuild.dockertest.domain.dto.ArticleResponse;
import com.example.bbsbuild.dockertest.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // servlet에 요청(get, post, put, delete) 보내는 모듈
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    ArticleService articleService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(status().isOk())
                .alwaysExpect(jsonPath("$.id").exists())
                .alwaysExpect(jsonPath("$.title").exists())
                .alwaysExpect(jsonPath("$.content").exists())
                .alwaysDo(print())
                .build();
    }

    long articleResponseId = 1;
    String articleResponseTitle =  "반응의title";
    String articleResponseContent = "반응의content";

    ArticleResponse articleResponse = new ArticleResponse(articleResponseId, articleResponseTitle, articleResponseContent);

    long articleReqeustId = 1;
    String articleReqeustTitle =  "요청의title";
    String articleReqeustContent = "요청의content";

    ArticleRequest articleRequest = new ArticleRequest(articleReqeustTitle, articleReqeustContent);


    @Test
    @DisplayName("id로 JSON이 잘 찾아와지나")
    void getArticleById() throws Exception {
        given(articleService.getOneById(articleResponseId)).willReturn(articleResponse);

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value(articleResponseTitle))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value(articleResponseContent))
                .andDo(print());
    }

    @Test
    @DisplayName("글 등록이 잘 되는지")
    void create() throws Exception {
        given(articleService.create(any(ArticleRequest.class))).willReturn(articleResponse);

        // mockMVC에 요청을 JSON형식으로 넣는 부분
        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new ArticleRequest(articleReqeustTitle, articleReqeustContent))
                        ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value(articleResponseTitle))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value(articleResponseContent))
                .andDo(print());
    }
}