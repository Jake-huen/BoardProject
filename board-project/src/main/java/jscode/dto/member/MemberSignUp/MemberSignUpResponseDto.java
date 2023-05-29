package jscode.dto.member.MemberSignUp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberSignUpResponseDto {

    private Long id;
    private String email;
    private LocalDateTime localDateTime;
}
