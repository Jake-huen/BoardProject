package jscode.domain;

import jscode.dto.article.ArticleDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "article")
    private List<ArticleComments> articleComments = new ArrayList<>();

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime updatedAt;

    @Builder
    public Article(Long id, Member member, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(ArticleDto articleDto) {
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.updatedAt = LocalDateTime.now();
    }

}
