package jscode.repository.impl;

import jscode.domain.Article;
import jscode.domain.dto.ArticleDto;
import java.util.List;

public interface ArticleDAO {

    Article insertArticle(Article article);

    List<Article> selectAllArticle();

    List<Article> findTopNByOrderByCreatedAtDesc(int n);

    Article selectArticle(Long id);

    Article updateArticle(ArticleDto articleDto) throws Exception;

    List<Article> searchTopNOrderByCreatedAtDesc(String keyword, int n);

    void deleteArticle(Long id) throws Exception;
}
