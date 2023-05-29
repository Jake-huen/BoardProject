package jscode.dto.member.MemberLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
