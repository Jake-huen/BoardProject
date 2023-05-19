package jscode.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ArticleDto {

    private final Long id;

    @NotBlank(message = "게시글 제목 조건 오류")
    @Size(min = 1, max = 15)
    private final String title;

    @NotBlank(message = "게시글 내용 조건 오류")
    @Size(min = 1, max = 1000)
    private final String content;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
