package jscode.service;

import jscode.domain.Article;
import jscode.domain.dto.ArticleDto;
import jscode.repository.ArticleDAOImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDAOImpl articleDAO;

    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();
        Article savedArticle = articleDAO.insertArticle(article);

        return new ArticleDto(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent(), savedArticle.getCreatedAt(), savedArticle.getUpdatedAt());
    }

    public ArticleDto getArticle(Long id){
        Article article = articleDAO.selectArticle(id);
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getUpdatedAt());
    }

    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleDAO.selectAllArticle();
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.stream().forEach(article -> articleDtos.add(new ArticleDto(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getUpdatedAt())));
        return articleDtos;
//        return articleDAO.selectAllArticle().stream()
//                .map(article -> new ArticleDto(article.getTitle(), article.getContent()))
//                .collect(Collectors.toList());
    }

    public ArticleDto updateArticle(ArticleDto articleDto) throws Exception {
        Article selectedArticle = articleDAO.updateArticle(articleDto);
        return new ArticleDto(selectedArticle.getId(), selectedArticle.getTitle(), selectedArticle.getContent(), selectedArticle.getCreatedAt(), selectedArticle.getUpdatedAt());
    }

    public void deleteArticle(Long id) throws Exception{
        articleDAO.deleteArticle(id);
    }
}
