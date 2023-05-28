package jscode.repository.dao.impl;

import jscode.domain.Article;
import jscode.dto.ArticleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleDAO {

    Article insertArticle(Article article);

    List<Article> selectAllArticle();

    List<Article> findTopNByOrderByCreatedAtDesc(int n);

    Article selectArticle(Long id);

    Article updateArticle(ArticleDto articleDto) throws RuntimeException;

    List<Article> searchTopNOrderByCreatedAtDesc(Pageable page, String keyword, int n);

    void deleteArticle(Long id) throws RuntimeException;
}
