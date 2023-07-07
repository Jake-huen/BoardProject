package jscode.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ArticleController - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class ArticleControllerTest {

    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 단일 조회")
    @Test
    void givenArticleId_whenRequestingArticle_thenReturnsArticlesJsonResponse() throws Exception {
        // Given
        Long id = 1L;

        // When & Then
        mvc.perform(get("/article?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[api] 게시글 모두 조회")
    @Test
    void givenNothing_whenRequestingAllArticles_thenReturnArticles() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/article/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[api] 게시글 모두 분류")
    @Test
    void givenNothing_whenRequestingAllArticlesSorted_thenReturnArticlesSorted() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/article/sortedAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("[api] 게시글 제목으로 검색")
    @Test
    void givenTitle_whenRequestingSelectedArticle_thenReturnArticle() throws Exception {
        // Given
        // When & Then
        mvc.perform(get("/article/sortedAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
