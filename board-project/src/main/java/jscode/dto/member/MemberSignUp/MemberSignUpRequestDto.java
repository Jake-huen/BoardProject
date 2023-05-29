package jscode.dto.member.MemberSignUp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberSignUpRequestDto {

    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
    @Email
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 8, max = 15)
    private String password;

    private MemberSignUpRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberSignUpRequestDto create(String email, String password) {
        return new MemberSignUpRequestDto(email, password);
    }
}
