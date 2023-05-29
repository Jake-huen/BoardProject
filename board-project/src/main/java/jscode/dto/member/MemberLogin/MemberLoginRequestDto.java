package jscode.dto.member.MemberLogin;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
