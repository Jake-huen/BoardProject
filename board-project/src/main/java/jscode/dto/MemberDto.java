package jscode.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDto {

    private final Long memberId;

    private final String email;
    private final String password;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
