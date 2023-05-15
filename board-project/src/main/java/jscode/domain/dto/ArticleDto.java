package jscode.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ArticleDto {
    private final Long id;
    private final String title;
    private final String content;
}
