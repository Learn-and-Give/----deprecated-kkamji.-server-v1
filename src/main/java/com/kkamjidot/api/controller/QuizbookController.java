package com.kkamjidot.api.controller;

import com.kkamjidot.api.dto.response.QuizbookDetailResponseDto;
import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import com.kkamjidot.api.service.QuizbookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;

    @Operation(summary = "주차별 문제집 모음 조회", description = "주차가 주어지면 문제집 제목, 설명, 제작자, 문제수를 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizbookResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "week", description = "주차", required = true, in = ParameterIn.QUERY, example = "1")
    })
    @GetMapping("v1/quizbooks")
    public ResponseEntity<?> findQuizbooksByWeek(@RequestHeader(value = "code") String code, @RequestParam(value = "week") int week) {
        try {
            List<QuizbookResponseDto> quizbooksByWeek = quizbookService.findQuizbooksByWeek(week);
            return ResponseEntity.ok(quizbooksByWeek);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();     // 그 주차에 문제집 없음
        }
    }

    @Operation(summary = "문제집 상세 조회", description = "문제집 아이디로 문제집 정보와 문제 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizbookDetailResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("v1/quizbooks/{quizbookId}")
    public ResponseEntity<?> findQuizbook(@RequestHeader(value = "code") String code, @PathVariable(value = "quizbookId") Long quizbookId) {
        return null;
    }
}
