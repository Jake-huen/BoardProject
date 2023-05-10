package jscode.repository;

import jscode.domain.Article;
import jscode.repository.impl.ArticleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticleDAOImpl implements ArticleDAO {

    private final ArticleRepository articleRepository;

    @Override
    public Article insertArticle(Article article) {
        Article savedArticle = articleRepository.save(article);
        return savedArticle;
    }

    @Override
    public List<Article> selectAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }

    @Override
    public Article selectArticle(Long id) {
        Optional<Article> selectedArticle = articleRepository.findById(id);
        return selectedArticle.get();
    }

    @Override
    public Article updateArticle(Long id, String title, String content) throws Exception {
        Optional<Article> selectedArticle = articleRepository.findById(id);
        Article updatedArticle;
        if (selectedArticle.isPresent()) {
            Article article = selectedArticle.get();
            article.setTitle(title);
            article.setTitle(content);
            updatedArticle = articleRepository.save(article);
        } else {
            throw new Exception();
        }

        return updatedArticle;
    }

    @Override
    public void deleteArticle(Long id) throws Exception {
        Optional<Article> selectedArticle = articleRepository.findById(id);
        if (selectedArticle.isPresent()) {
            Article article = selectedArticle.get();
            articleRepository.delete(article);
        }else {
            throw new Exception();
        }
    }
}
