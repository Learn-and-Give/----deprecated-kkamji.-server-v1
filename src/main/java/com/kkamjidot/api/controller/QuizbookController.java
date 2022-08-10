package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.domain.Readable;
import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import com.kkamjidot.api.service.MemberService;
import com.kkamjidot.api.service.QuizbookService;
import com.kkamjidot.api.service.ReadableService;
import com.kkamjidot.api.service.VerifyApiService;
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
@RequestMapping("v1/chapters/{chapterId}/quizbooks")
@RestController
public class QuizbookController {
    private final QuizbookService quizbookService;
    private final MemberService memberService;
    private final ReadableService readableService;
    private final VerifyApiService verifyApiService;

    @Operation(summary = "챕터별 문제집 모음 조회 API", description = "챕터가 주어지면 문제집 제목, 설명, 제작자, 문제수를 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuizbookResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제집입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 챕터별 문제집 모음입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping
    public ResponseEntity<List<QuizbookResponseDto>> readQuizbooks(@RequestHeader(value = "code") String code, @PathVariable(value = "chapterId") Long chapterId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 주차별 문제집 조회
        List<Quizbook> quizbooks = quizbookService.findQuizbooks(chapterId);

        // api 검증
        verifyApiService.verifyApiQuizbookToChapter(quizbooks.get(0), chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(quizbooks.stream().map(quizbook -> QuizbookResponseDto.of(quizbook)).collect(Collectors.toList()));
    }

    @Operation(summary = "문제집 정보 조회 API", description = "문제집 아이디로 문제집 정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QuizbookResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(example = "{message: 열람할 수 없는 문제집입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제집입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "quizbookId", description = "문제집 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("{quizbookId}")
    public ResponseEntity<QuizbookResponseDto> readQuizbook(@RequestHeader(value = "code") String code,
                                                            @PathVariable(value = "chapterId") Long chapterId,
                                                            @PathVariable(value = "quizbookId") Long quizbookId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 열람할 수 있는 챕터 체크
        readableService.isReadable(chapterId, member);

        // 문제집 정보 조회
        Quizbook quizbook = quizbookService.findOne(quizbookId);

        // api 검증
        verifyApiService.verifyApiQuizbookToChapter(quizbook, chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(QuizbookResponseDto.of(quizbook));
    }
}
