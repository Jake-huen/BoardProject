package jscode.dto.member.MemberLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String email;
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
