package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.dto.request.LoginRequestDto;
import com.kkamjidot.api.dto.response.LoginResponseDto;
import com.kkamjidot.api.dto.response.QuizbooksByWeekResponseDto;
import com.kkamjidot.api.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "로그인", description = "회원 여부를 확인한다.")
    @PostMapping("v1/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED")
    })
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto request) {
        try {
            Member member = memberService.login(request.getName(), request.getCode());  // 로그인
            LoginResponseDto response = LoginResponseDto.builder()      // 응답 객체 생성
                    .userId(member.getId())
                    .name(member.getMemberName())
                    .build();
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();     // 존재하지 않는 회원입니다.
        }
    }
}
