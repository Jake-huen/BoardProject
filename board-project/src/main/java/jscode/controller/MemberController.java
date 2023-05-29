package jscode.controller;

import jscode.dto.member.MemberLogin.MemberLoginResponseDto;
import jscode.dto.member.MemberSignUp.MemberSignUpRequestDto;
import jscode.dto.member.MemberLogin.MemberLoginRequestDto;
import jscode.dto.member.MemberSignUp.MemberSignUpResponseDto;
import jscode.dto.security.TokenInfo;
import jscode.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public MemberLoginResponseDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        return memberService.login(email, password);
    }

    @PostMapping("/signup")
    public MemberSignUpResponseDto signup(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto) {
        String email = memberSignUpRequestDto.getEmail();
        String password = memberSignUpRequestDto.getPassword();
        return memberService.signup(email, password);
    }
}
