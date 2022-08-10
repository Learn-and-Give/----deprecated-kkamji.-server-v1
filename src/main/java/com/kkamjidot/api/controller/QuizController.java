package com.kkamjidot.api.controller;

import com.kkamjidot.api.dto.response.QuizContentResponseDto;
import com.kkamjidot.api.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes")
@RestController
public class QuizController {
    private final MemberService memberService;

    @Operation(summary = "문제집 내 문제들 개요 모음 조회 API", description = "문제집의 문제들의 제목, ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizSummaryResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED"),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping
    public


//    @Operation(summary = "문제 내용 조회 API", description = "문제의 제목과 내용을 조회한다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizContentResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "UNATHORIZED"),
//            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND")
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
//    })
//    @GetMapping("{quizId}/content")
//    public ResponseEntity<?> findQuizContent(@RequestHeader(value = "code") String code, @PathVariable(value = "quizId") Long quizId) {
//        try {
//            memberService.authorization(code);      // 회원 인가 체크
//
//            // 문제 내용 조회
//            QuizContentResponseDto quizContent = new QuizContentResponseDto(quizService.findQuizById(quizId));
//            return ResponseEntity.ok(quizContent);
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));  // UNATHORIZED
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // DATA NOT FOUND
//        }
//    }
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
//    @Operation(summary = "문제 풀기 API", description = "문제를 맞았는지 틀렸는지 제출한다. 단, 한 번만 가능하다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SolveQuizResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "UNATHORIZED : 존재하지 않는 회원입니다."),
//            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND : 존재하지 않는 문제입니다."),
//            @ApiResponse(responseCode = "409", description = "CONFLICT : 이미 풀었습니다.")
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
//    })
//    @PostMapping("{quizId}/solve")
//    public ResponseEntity<?> SolveQuiz(@RequestHeader(value = "code") String code, @PathVariable(value = "quizId") Long quizId, @RequestBody @Valid SolveQuizRequestDto request) {
//        try {
//            memberService.authorization(code);    // 회원 인가 체크
//
//            // 문제 풀기
//            Solve solve = solveService.solve(code, quizId, request.isCorrect());
//            return ResponseEntity.ok(SolveQuizResponseDto.builder()
//                    .userId(solve.getMember().getId())              // 회원 아이디
//                    .userName(solve.getMember().getMemberName())    // 회원 이름
//                    .quizId(quizId)                                 // 문제 아이디
//                    .solveIsCorrect(solve.getSolveIsCorrected())    // 문제 풂 여부 조회
//                    .build());
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));  // UNATHORIZED : 존재하지 않는 회원입니다.
//        } catch (KeyAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));     // CONFLICT : 이미 풀었습니다.
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // DATA NOT FOUND : 존재하지 않는 문제입니다.
//        }
//    }
//
//    @Operation(summary = "퀴즈 정답 및 해설 조회 API", description = "퀴즈의 정답/해설/출처를 조회한다. 단, 풀지 않았을 경우 보이지 않는다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizAnswerResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "UNATHORIZED : 존재하지 않는 회원입니다."),
//            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND : 존재하지 않는 문제입니다.")
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
//    })
//    @GetMapping("{quizId}/answer")
//    public ResponseEntity<?> findQuizAnswer(@RequestHeader(value = "code") String code, @PathVariable(value = "quizId") Long quizId) {
//        try {
//            memberService.authorization(code);    // 회원 인가 체크
//
//            // 문제 정답 조회
//            Quiz quiz = quizService.findQuizById(quizId);
//            return ResponseEntity.ok(new QuizAnswerResponseDto(quiz));
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));  // UNATHORIZED : 존재하지 않는 회원입니다.
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // DATA NOT FOUND : 존재하지 않는 문제입니다.
//        }
//    }
//}
