package jscode.controller;

import jscode.domain.dto.ArticleDto;
import jscode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @PostMapping()
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.saveArticle(articleDto));
    }

    @PostMapping("/change")
    public ResponseEntity<ArticleDto> changeArticle(@RequestBody ArticleDto articleDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.updateArticle(articleDto.getId(),articleDto.getTitle(),articleDto.getContent()));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteArticle(Long id) throws Exception {
        articleService.deleteArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
