package jscode.repository.impl;

import jscode.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {

    Article insertArticle(Article article);

    List<Article> selectAllArticle();

    Article selectArticle(Long id);

    Article updateArticleTitle(Long id, String title) throws Exception;

    Article updateArticleContent(Long id, String content) throws Exception;

    void deleteArticle(Long id) throws Exception;
}
