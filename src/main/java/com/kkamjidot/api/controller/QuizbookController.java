package com.kkamjidot.api.controller;

import com.kkamjidot.api.dto.response.QuizbooksByWeekResponseDto;
import com.kkamjidot.api.service.QuizbookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;

    @ApiOperation(value = "주차별 문제집 모음 조회", notes = "주차가 주어지면 문제집 제목, 설명, 제작자, 문제수를 반환한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "로그인한 회원 코드", required = true, dataType = "String", paramType = "header", example = "1234"),
            @ApiImplicitParam(name = "week", value = "주차", required = true, dataType = "int", paramType = "query", example = "1")
    })
    @GetMapping("v1/quizbooks")
    public ResponseEntity<?> findQuizbooksByWeek(@RequestHeader(value = "code") String code, @RequestParam(value = "week") int week) {
        try {
            List<QuizbooksByWeekResponseDto> quizbooksByWeek = quizbookService.findQuizbooksByWeek(week);
            return ResponseEntity.ok(quizbooksByWeek);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
