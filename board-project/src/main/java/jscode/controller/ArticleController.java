package jscode.controller;

import jscode.domain.Article;
import jscode.domain.dto.ArticleDto;
import jscode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping()
    public ResponseEntity<ArticleDto> getArticle(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticle(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
    }

    @GetMapping("/sortedAll")
    public ResponseEntity<List<ArticleDto>> getSortedAllArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getSortedAllArticles());
    }

    @GetMapping("/search-title")
    public ResponseEntity<List<ArticleDto>> searchArticles(Pageable page, @NotBlank @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.searchArticles(page, keyword));
    }

    @PostMapping()
    public ResponseEntity<ArticleDto> createArticle(@Valid @RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.saveArticle(articleDto));
    }

    @PostMapping("/change")
    public ResponseEntity<ArticleDto> changeArticle(@Valid @RequestBody ArticleDto articleDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.updateArticle(articleDto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteArticle(Long id) throws Exception {
        articleService.deleteArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
