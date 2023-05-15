package jscode.repository.impl;

import jscode.domain.Article;
import jscode.domain.dto.ArticleDto;
import java.util.List;

public interface ArticleDAO {

    Article insertArticle(Article article);

    List<Article> selectAllArticle();

    Article selectArticle(Long id);

    Article updateArticle(ArticleDto articleDto) throws Exception;

    void deleteArticle(Long id) throws Exception;
}
