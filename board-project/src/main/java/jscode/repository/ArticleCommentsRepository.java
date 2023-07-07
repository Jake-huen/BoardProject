package jscode.repository;

import jscode.domain.ArticleComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentsRepository extends JpaRepository<ArticleComments,Long> {
}
