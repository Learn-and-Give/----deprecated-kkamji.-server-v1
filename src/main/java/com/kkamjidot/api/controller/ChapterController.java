package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Chapter;
import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.dto.response.ChapterResponseDto;
import com.kkamjidot.api.service.ChapterService;
import com.kkamjidot.api.service.MemberService;
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
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("v1")
@RestController
public class ChapterController {
    private final ChapterService chapterService;
    private final MemberService memberService;

    @Operation(summary = "챕터 모음 조회 API", description = "현재 챌린지의 모든 챕터 정보와 열람 가능 여부를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChapterResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 챕터가 존재하지 않습니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234")
    })
    @GetMapping("chapters")
    public ResponseEntity<List<ChapterResponseDto>> readChapters(@RequestHeader(value = "code") String code) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 챕터 모음 조회
        List<Chapter> chapters = chapterService.findAll();

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(chapters.stream()
                .map(chapter -> ChapterResponseDto.of(chapter, member))
                .collect(Collectors.toList()));
    }

    @Operation(summary = "챕터 정보 조회 API", description = "챕터 정보와 열람 가능 여부를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChapterResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "UNATHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 챕터입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "chapterId", description = "챕터 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("chapters/{chapterId}")
    public ResponseEntity<ChapterResponseDto> readChapter(@RequestHeader(value = "code") String code, @PathVariable(value = "chapterId") Long chapterId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 챕터 모음 조회
        Chapter chapter = chapterService.findOne(chapterId);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(ChapterResponseDto.of(chapter, member));
    }
}
