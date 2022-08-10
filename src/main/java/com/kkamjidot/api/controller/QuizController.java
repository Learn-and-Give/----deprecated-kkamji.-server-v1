package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.File;
import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.dto.response.QuizAnswerResponseDto;
import com.kkamjidot.api.dto.response.QuizResponseDto;
import com.kkamjidot.api.dto.response.QuizSummaryResponseDto;
import com.kkamjidot.api.service.*;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes")
@RestController
public class QuizController {
    private final MemberService memberService;
    private final QuizService quizService;
    private final ReadableService readableService;
    private final VerifyApiService verifyApiService;

    @Operation(summary = "문제집 내 문제들 개요 모음 조회 API", description = "문제집의 문제들의 제목, ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizSummaryResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping
    public ResponseEntity<List<QuizSummaryResponseDto>> readQuizSummaries(@RequestHeader(value = "code") String code,
                                                                          @PathVariable(value = "chapterId") Long chapterId,
                                                                          @PathVariable(value = "quizbookId") Long quizbookId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제집 개요 모음 조회
        List<Quiz> quizzes = quizService.findQuizzes(quizbookId);

        // api 검증
        verifyApiService.verifyApiQuizToChapter(quizzes.get(0), quizbookId, chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(quizzes.stream().map(quiz -> QuizSummaryResponseDto.of(quiz, member)).collect(Collectors.toList()));
    }



    @Operation(summary = "문제 조회 API", description = "문제의 정보와 내용을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizSummaryResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("{quizId}")
    public ResponseEntity<QuizResponseDto> readQuiz(@RequestHeader(value = "code") String code,
                                                    @PathVariable(value = "chapterId") Long chapterId,
                                                    @PathVariable(value = "quizbookId") Long quizbookId,
                                                    @PathVariable(value = "quizId") Long quizId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizId);

        // api 검증
        verifyApiService.verifyApiQuizToChapter(quiz, quizbookId, chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(QuizResponseDto.of(quiz, member));
    }

    @Operation(summary = "문제 정답 조회 API", description = "문제의 정답/해설/출처를 조회한다. 단, 풀지 않았을 경우 보이지 않는다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizAnswerResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("{quizId}/answer")
    public ResponseEntity<QuizAnswerResponseDto> readQuizAnswer(@RequestHeader(value = "code") String code,
                                                          @PathVariable(value = "chapterId") Long chapterId,
                                                          @PathVariable(value = "quizbookId") Long quizbookId,
                                                          @PathVariable(value = "quizId") Long quizId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizId);

        // api 검증
        verifyApiService.verifyApiQuizToChapter(quiz, quizbookId, chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(QuizAnswerResponseDto.of(quiz));
    }
//
//    @Operation(summary = "문제 풂 여부 조회 API", description = "푼 문제인지 확인한다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = IsQuizSolvedResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "UNATHORIZED"),
//            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
//    })
//    @GetMapping("{quizId}/is-solved")
//    public ResponseEntity<?> isQuizSolved(@RequestHeader(value = "code") String code, @PathVariable(value = "quizId") Long quizId) {
//        try {
//            Member member = memberService.findOne(code);    // 회원 객체 조회 및 인가 체크
//
//            // 응답 반환
//            return ResponseEntity.ok(IsQuizSolvedResponseDto.builder()
//                    .userId(member.getId())
//                    .userName(member.getMemberName())
//                    .quizId(quizId)
//                    .quizIsSolved(solveService.isSolved(member.getId(), quizId))    // 문제 풂 여부 조회
//                    .build());
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));  // UNATHORIZED
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // DATA NOT FOUND
//        }
//    }
//
}
