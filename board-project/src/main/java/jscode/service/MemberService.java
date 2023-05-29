package jscode.service;

import jscode.config.security.JwtTokenProvider;
import jscode.domain.Member;
import jscode.dto.member.MemberLogin.MemberLoginResponseDto;
import jscode.dto.member.MemberSignUp.MemberSignUpRequestDto;
import jscode.dto.member.MemberSignUp.MemberSignUpResponseDto;
import jscode.dto.security.TokenInfo;
import jscode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberLoginResponseDto login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return new MemberLoginResponseDto(email, tokenInfo.getGrantType(), tokenInfo.getAccessToken(), tokenInfo.getRefreshToken());
    }

    @Transactional
    public MemberSignUpResponseDto signup(String email, String password) {
        // 1. 중복된 이메일 체크
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("중복된 이메일이 존재합니다");
        }

        // 2. 회원 생성
        Member member = Member.builder()
                .email(email)
                .password(password)
                .roles(Arrays.asList("USER"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        // 3. 회원 저장
        Member savedMember = memberRepository.save(member);

        // 4. 회원가입 응답 생성
        MemberSignUpResponseDto responseDto = new MemberSignUpResponseDto(savedMember.getId(), savedMember.getEmail(), savedMember.getCreatedAt());

        return responseDto;
    }
}
