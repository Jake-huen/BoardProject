package jscode.repository.dao;

import jscode.repository.dao.impl.ArticleDAO;
import jscode.domain.Article;
import jscode.dto.ArticleDto;
import jscode.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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
    public List<Article> findTopNByOrderByCreatedAtDesc(int n) {
        Pageable pageable = PageRequest.of(0, n, Sort.by("createdAt").descending());
        Page<Article> page = articleRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Article selectArticle(Long id) {
        Optional<Article> selectedArticle = articleRepository.findById(id);
        return selectedArticle.get();
    }

    @Override
    public Article updateArticle(ArticleDto articleDto) throws RuntimeException {
        Optional<Article> selectedArticle = articleRepository.findById(articleDto.getId());
        selectedArticle.get().update(articleDto);
        return articleRepository.save(selectedArticle.get());
    }

    @Override
    public List<Article> searchTopNOrderByCreatedAtDesc(Pageable page, String keyword, int n) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), n, Sort.by("createdAt").descending());
        return articleRepository.findArticleByTitleContaining(keyword, pageable);
    }

    @Override
    public void deleteArticle(Long id) throws RuntimeException {
        Optional<Article> selectedArticle = articleRepository.findById(id);
        if (selectedArticle.isPresent()) {
            Article article = selectedArticle.get();
            articleRepository.delete(article);
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다");
        }
    }
}
