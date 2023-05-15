package jscode.repository;

import jscode.domain.Article;
import jscode.domain.dto.ArticleDto;
import jscode.repository.impl.ArticleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
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
    public Article updateArticle(ArticleDto articleDto) throws Exception {
        Optional<Article> selectedArticle = articleRepository.findById(articleDto.getId());
        if (selectedArticle.isPresent()) {
            String title = selectedArticle.get().getTitle();
            String content = selectedArticle.get().getContent();
            if(articleDto.getTitle()!=null){
                title = articleDto.getTitle();
            }
            if (articleDto.getContent() != null) {
                content = articleDto.getContent();
            }
            Article updatedArticle = Article.builder()
                    .id(selectedArticle.get().getId())
                    .title(title)
                    .content(content)
                    .createdAt(selectedArticle.get().getCreatedAt())
                    .updatedAt(articleDto.getUpdatedAt())
                    .build();
            return articleRepository.save(updatedArticle);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Article> searchTopNOrderByCreatedAtDesc(Pageable page, String keyword, int n) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), n, Sort.by("createdAt").descending());
        return articleRepository.findByTitleContainingOrderByCreatedAtDesc(keyword, pageable);
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
