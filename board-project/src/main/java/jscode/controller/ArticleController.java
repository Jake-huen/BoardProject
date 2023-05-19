package jscode.controller;

import jscode.domain.dto.ArticleDto;
import jscode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto getArticle(Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/sortedAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleDto> getSortedAllArticles() {
        return articleService.getSortedAllArticles();
    }

    @GetMapping("/search-title")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleDto> searchArticles(Pageable page, @RequestParam String keyword) {
        return articleService.searchArticles(page, keyword);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto createArticle(@Valid @RequestBody ArticleDto articleDto) {
        return articleService.saveArticle(articleDto);
    }

    @PostMapping("/change")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto changeArticle(@Valid @RequestBody ArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public String deleteArticle(Long id) throws RuntimeException {
        articleService.deleteArticle(id);
        return "정상적으로 삭제되었습니다.";
    }
}
