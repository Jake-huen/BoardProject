package jscode.repository.impl;

import jscode.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {

    Article insertArticle(Article article);

    List<Article> selectAllArticle();

    Article selectArticle(Long id);

    Article updateArticle(Long id, String title, String content) throws Exception;

    void deleteArticle(Long id) throws Exception;
}
