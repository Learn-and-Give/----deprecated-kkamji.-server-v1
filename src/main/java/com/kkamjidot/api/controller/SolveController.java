package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Solve;
import com.kkamjidot.api.dto.request.SolveQuizRequestDto;
import com.kkamjidot.api.dto.response.SolveQuizResponseDto;
import com.kkamjidot.api.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes/{quizId}/solve")
@RestController
public class SolveController {
    private final MemberService memberService;
    private final QuizService quizService;
    private final ReadableService readableService;
    private final VerifyApiService verifyApiService;
    private final SolveService solveService;

    @Operation(summary = "문제 풀기 API", description = "문제를 맞았는지 틀렸는지 제출한다. 단, 한 번만 가능하다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SolveQuizResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}"))),
            @ApiResponse(responseCode = "409", description = "CONFLICT : 이미 풀었습니다.", content = @Content(schema = @Schema(example = "{message: 이미 풀었습니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")

    })
    @PostMapping
    public ResponseEntity<SolveQuizResponseDto> solveQuiz(@RequestHeader(value = "code") String code,
                                                          @PathVariable(value = "chapterId") Long chapterId,
                                                          @PathVariable(value = "quizbookId") Long quizbookId,
                                                          @PathVariable(value = "quizId") Long quizId,
                                                          @RequestBody @Valid SolveQuizRequestDto request) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizId);

        // api 검증
        verifyApiService.verifyApiQuizToChapter(quiz, quizbookId, chapterId);

        // 문제 풀기
        Solve solve = solveService.saveOne(quiz, member, request.getSolveIsCorrect());

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(SolveQuizResponseDto.of(solve));
    }
}
