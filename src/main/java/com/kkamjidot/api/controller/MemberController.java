package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.dto.request.LoginRequestDto;
import com.kkamjidot.api.dto.response.LoginResponseDto;
import com.kkamjidot.api.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://kkamjidot.com", "https://www.kkamjidot.com"}) // @CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("v1")
@RestController
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "로그인 API", description = "회원 여부를 확인한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED")
    })
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto request) {
        Member member = memberService.login(request.getName(), request.getCode());  // 로그인
        LoginResponseDto response = LoginResponseDto.of(member);
        return ResponseEntity.ok(response);
    }
}


