package jscode.controller;

import jscode.dto.article.ArticleDto;
import jscode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
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
    public ArticleDto createArticle(HttpServletRequest request, @Valid @RequestBody ArticleDto articleDto) {
        return articleService.saveArticle(request, articleDto);
    }

    @PostMapping("/change")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto changeArticle(HttpServletRequest request, @Valid @RequestBody ArticleDto articleDto) {
        return articleService.updateArticle(request, articleDto);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public String deleteArticle(Long id) throws RuntimeException {
        articleService.deleteArticle(id);
        return "정상적으로 삭제되었습니다.";
    }
}
