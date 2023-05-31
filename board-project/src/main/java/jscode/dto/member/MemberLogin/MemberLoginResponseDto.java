package jscode.dto.member.MemberLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String email;
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
