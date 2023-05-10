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
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());

        Article savedArticle = articleDAO.insertArticle(article);

        return new ArticleDto(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }

    public ArticleDto getArticle(Long id){
        Article article = articleDAO.selectArticle(id);
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleDAO.selectAllArticle();
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.stream().forEach(article -> articleDtos.add(new ArticleDto(article.getId(), article.getTitle(), article.getContent())));
        return articleDtos;
//        return articleDAO.selectAllArticle().stream()
//                .map(article -> new ArticleDto(article.getTitle(), article.getContent()))
//                .collect(Collectors.toList());
    }

    public ArticleDto updateArticle(Long id, String title, String content) throws Exception {
        Article selectedArticle = articleDAO.updateArticle(id, title, content);
        selectedArticle.setTitle(title);
        selectedArticle.setContent(content);
        return new ArticleDto(selectedArticle.getId(), selectedArticle.getTitle(), selectedArticle.getContent());
    }

    public void deleteArticle(Long id) throws Exception{
        articleDAO.deleteArticle(id);
    }
}
