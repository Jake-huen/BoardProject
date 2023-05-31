package jscode.dto.member.MemberSignUp;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
public class MemberSignUpRequestDto {

    @Email(message = "이메일 형식이 틀렸습니다")
    @Pattern(regexp = "\\S+", message = "이메일에 공백이 없어야 합니다.")
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 8, max = 15)
    private String password;

    public static MemberSignUpRequestDto create(String email, String password) {
        return new MemberSignUpRequestDto(email, password);
    }
}
