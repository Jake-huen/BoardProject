package jscode.dto.member;

import jscode.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class MemberDto {
    private String email;
    private LocalDateTime createdTime;
}
