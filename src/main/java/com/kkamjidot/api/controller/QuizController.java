package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.dto.request.CreateQuizRequestDto;
import com.kkamjidot.api.dto.request.UpdateAnswerRequestDto;
import com.kkamjidot.api.dto.response.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class QuizController {
    private final MemberService memberService;
    private final QuizService quizService;
    private final ReadableService readableService;
    private final VerifyApiService verifyApiService;
    private final QuizbookService quizbookService;

    @Operation(summary = "문제집 내 문제들 개요 모음 조회 API", description = "문제집의 문제들의 제목, ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizSummaryResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
    })
    @GetMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes")
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
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes/{quizId}")
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
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes/{quizId}/answer")
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

    @Operation(summary = "문제집의 n번째 문제 아이디 반환 API", description = "한 문제집의 n번째 문제가 무슨 문제인지 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(example = "{quizId: 1}"))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제집입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제집입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "number", description = "순서", required = false, in = ParameterIn.QUERY, example = "1")
    })
    @GetMapping("v1/chapters/{chapterId}/quizbooks/{quizbookId}/quizzes/search")
    public ResponseEntity<Map<String, Long>> readQuizId(@RequestHeader(value = "code") String code,
                                                        @PathVariable(value = "chapterId") Long chapterId,
                                                        @PathVariable(value = "quizbookId") Long quizbookId,
                                                        @RequestParam(value = "number", required = false, defaultValue = "1") Integer number) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizbookId, number);

        // api 검증
        verifyApiService.verifyApiQuizToChapter(quiz, quizbookId, chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(Map.of("quizId", quiz.getId()));
    }

    @Operation(summary = "문제 정답 수정 API", description = "문제의 정답/해설/출처를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(example = "{quizId: 1}")))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1"),
    })
    @PatchMapping("v1/quizzes/{quizId}/answer")
    public ResponseEntity<Map<String, Long>> updateQuizAnswer(@RequestHeader(value = "code") String code,
                                                              @PathVariable(value = "quizId") Long quizId,
                                                              @RequestBody @Valid UpdateAnswerRequestDto requestDto) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 문제 수정
        Quiz quiz = quizService.updateOne(quizId, member, requestDto);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(Map.of("quizId", quiz.getId()));
    }

//    @Operation(summary = "문제 제출 API", description = "문제를 제출한다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CreateQuizResponseDto.class)))),
//            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
//    })
//    @Parameters({
//            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
//            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1"),
//    })
//    @PostMapping("v1/quizbooks/{quizbookId}/quizzes")
//    public ResponseEntity<CreateQuizResponseDto> createQuiz(@RequestHeader(value = "code") String code,
//                                                            @RequestBody @Valid CreateQuizRequestDto requestDto,
//                                                            MultipartFile[] uploadFiles) {
//        // 회원 객체 조회 및 인가 체크
//        Member member = memberService.findOne(code);
//
//        // 문제집 조회 *Deprecated*
//        Quizbook quizbook = quizbookService.findOne(member);
//
//        // 문제 수정
//        Long id = quizService.createOne(requestDto, quizbook);
//
//
//
//
//        // 응답 객체 생성 및 반환
//        return ResponseEntity.ok(CreateQuizResponseDto.builder().quizId(id).build());
//    }
}
