package jscode.dto.member.MemberSignUp;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
public class MemberSignUpRequestDto {

    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
    @Email
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 8, max = 15)
    private String password;

    public static MemberSignUpRequestDto create(String email, String password) {
        return new MemberSignUpRequestDto(email, password);
    }
}
