package jscode.controller;

import jscode.dto.member.MemberLogin.MemberLoginResponseDto;
import jscode.dto.member.MemberSignUp.MemberSignUpRequestDto;
import jscode.dto.member.MemberLogin.MemberLoginRequestDto;
import jscode.dto.member.MemberSignUp.MemberSignUpResponseDto;
import jscode.dto.security.TokenInfo;
import jscode.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MemberLoginResponseDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        return memberService.login(email, password);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberSignUpResponseDto signup(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto) {
        String email = memberSignUpRequestDto.getEmail();
        String password = memberSignUpRequestDto.getPassword();
        return memberService.signup(email, password);
    }
}
