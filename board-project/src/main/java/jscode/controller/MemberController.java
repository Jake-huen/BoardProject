package jscode.controller;

import jscode.dto.member.MemberDto;
import jscode.dto.member.MemberLogin.MemberLoginResponseDto;
import jscode.dto.member.MemberSignUp.MemberSignUpRequestDto;
import jscode.dto.member.MemberLogin.MemberLoginRequestDto;
import jscode.dto.member.MemberSignUp.MemberSignUpResponseDto;
import jscode.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public MemberDto checkMember(HttpServletRequest request) {
        return memberService.checkMember(request);
    }

}
