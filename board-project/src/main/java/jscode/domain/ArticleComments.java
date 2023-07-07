package jscode.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ArticleComments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Member member;

    private String content;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime updatedAt;

}
