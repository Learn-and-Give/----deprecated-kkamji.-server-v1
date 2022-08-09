package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import com.kkamjidot.api.service.MemberService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/v1/chapters/{chapterId}/quizbooks")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;
//    private final QuizService quizService;
//    private final SolveService solveService;
    private final MemberService memberService;

    @Operation(summary = "챕터별 문제집 모음 조회 API", description = "챕터가 주어지면 문제집 제목, 설명, 제작자, 문제수를 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizbookResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 챕터별 문제집 모음입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping()
    public ResponseEntity<List<QuizbookResponseDto>> readQuizbooks(@RequestHeader(value = "code") String code, @PathVariable(value = "chapterId") Long chapterId) {
        // 회원 인가 체크
        memberService.authorization(code);

        // 주차별 문제집 조회
        List<Quizbook> quizbooks = quizbookService.findQuizbooks(chapterId);

        // 응답 반환
        return ResponseEntity.ok(quizbooks.stream().map(quizbook -> QuizbookResponseDto.of(quizbook)).collect(Collectors.toList()));
    }

//    @Operation(summary = "문제집 상세 조회 API", description = "문제집 아이디로 문제집 정보와 문제 ")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizbookDetailResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "UNATHORIZED"),
//            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1")
//    })
//    @GetMapping("v1/quizbooks/{quizbookId}")
//    public ResponseEntity<?> findQuizbook(@RequestHeader(value = "code") String code, @PathVariable(value = "quizbookId") Long quizbookId) {
//        try {
//            memberService.authorization(code);    // 인가 체크
//
//            // 문제집 상세 정보 응답 객체 생성
//            QuizbookDetailResponseDto quizbookDetail = quizbookService.findQuizbookDetailById(quizbookId);
//            List<Quiz> quizs = quizService.findQuizSummaryByQuizbookId(quizbookId);     // 문제집 내부 문제 리스트 조회
//
//            // 문제를 문제 개요 응답 객체로 변환
//            for (Quiz quiz : quizs) {
//                QuizSummaryResponseDto quizSummary = quizService.findQuizSummaryByQuizId(quiz.getId());
//                if (solveService.isSolvedByCode(code, quiz.getId())) quizSummary.solveQuiz();     // 문제 푼 적이 있으면 체크
//                quizbookDetail.getQuizSummaries().add(quizSummary);
//            }
//
//            return ResponseEntity.ok(quizbookDetail);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // DATA NOT FOUND
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));     // 잘못된 회원 접근
//        }
//    }
}
