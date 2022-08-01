package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.dto.request.LoginRequestDto;
import com.kkamjidot.api.dto.response.LoginResponseDto;
import com.kkamjidot.api.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "로그인", notes = "회원 여부를 확인한다.")
    @PostMapping("v1/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            Member member = memberService.login(request.getName(), request.getPassword());
            LoginResponseDto response = LoginResponseDto.builder()
                    .userId(member.getId())
                    .name(member.getName())
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
