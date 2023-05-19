package jscode.repository;

import jscode.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findArticleByTitleContaining(String keyword, Pageable pageable);
}
