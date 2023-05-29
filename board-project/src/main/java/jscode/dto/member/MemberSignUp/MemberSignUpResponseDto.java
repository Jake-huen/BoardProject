package jscode.dto.member.MemberSignUp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class MemberSignUpResponseDto {

    private Long id;
    private String email;
    private LocalDateTime createdTime;
}
