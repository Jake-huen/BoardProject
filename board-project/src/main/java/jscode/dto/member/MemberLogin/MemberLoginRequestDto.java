package jscode.dto.member.MemberLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
public class MemberLoginRequestDto {
    @Email(message = "이메일 형식이 틀렸습니다")
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 8, max = 15)
    private String password;
}
