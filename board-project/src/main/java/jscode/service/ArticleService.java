package jscode.service;

import jscode.domain.Article;
import jscode.domain.Member;
import jscode.dto.article.ArticleDto;
import jscode.dto.member.MemberDto;
import jscode.dto.member.MemberSignUp.MemberSignUpResponseDto;
import jscode.repository.ArticleRepository;
import jscode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    // 게시글 저장
    public ArticleDto saveArticle(HttpServletRequest request, ArticleDto articleDto) {
        MemberDto memberDto = memberService.checkMember(request);
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        Article article = Article.builder()
                .member(member.get())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();
        Article savedArticle = articleRepository.save(article);

        return new ArticleDto(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent(), member.get().getId(), savedArticle.getCreatedAt(), savedArticle.getUpdatedAt());
    }

    // 게시글 가져오기
    public ArticleDto getArticle(Long id) {
        Article article = articleRepository.findById(id).get();
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent(), article.getMember().getId(), article.getCreatedAt(), article.getUpdatedAt());
    }

    // 모든 게시글 id 순서로 가져오기
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.stream().forEach(article -> articleDtos.add(new ArticleDto(article.getId(), article.getTitle(), article.getContent(), null, article.getCreatedAt(), article.getUpdatedAt())));
        return articleDtos;
    }

    // 모든 게시글 최신순 100개까지 가져오기
    public List<ArticleDto> getSortedAllArticles() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by("createdAt").descending());
        Page<Article> page = articleRepository.findAll(pageable);
        List<Article> articles = page.getContent();
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.stream().forEach(article -> articleDtos.add(new ArticleDto(article.getId(), article.getTitle(), article.getContent(), null, article.getCreatedAt(), article.getUpdatedAt())));
        return articleDtos;
    }

    // 게시글 수정
    @Transactional
    public ArticleDto updateArticle(HttpServletRequest request, ArticleDto articleDto) {
        MemberDto memberDto = memberService.checkMember(request);
        Optional<Article> article = articleRepository.findById(articleDto.getId());
        article.get().update(articleDto);
        Article selectedArticle = articleRepository.save(article.get());
        return new ArticleDto(selectedArticle.getId(), selectedArticle.getTitle(), selectedArticle.getContent(), null, selectedArticle.getCreatedAt(), selectedArticle.getUpdatedAt());
    }

    // 게시글 검색
    public List<ArticleDto> searchArticles(Pageable page, String keyword) {
        if (keyword.isBlank()) {
            throw new RuntimeException("키워드는 한 글자 이상이여야 합니다");
        } else {
            Pageable pageable = PageRequest.of(page.getPageNumber(), 100, Sort.by("createdAt").descending());
            List<Article> articles = articleRepository.findArticleByTitleContaining(keyword, pageable);
            List<ArticleDto> articleDtos = new ArrayList<>();
            articles.stream().forEach(article -> articleDtos.add(new ArticleDto(article.getId(), article.getTitle(), article.getContent(), null, article.getCreatedAt(), article.getUpdatedAt())));
            return articleDtos;
        }
    }

    // 게시글 삭제
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
