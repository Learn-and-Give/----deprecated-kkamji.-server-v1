package com.kkamjidot.api.controller;

import com.kkamjidot.api.domain.Comment;
import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.dto.request.CommentRequestDto;
import com.kkamjidot.api.dto.response.CommentResponseDto;
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
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final MemberService memberService;
    private final QuizService quizService;

    private final CommentService commentService;

    @Operation(summary = "의견 생성 API", description = "문제에 의견을 단다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 문제입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @PostMapping("v1/quizzes/{quizId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestHeader(value = "code") String code,
                                                            @PathVariable(value = "quizId") Long quizId,
                                                            @RequestBody @Valid CommentRequestDto requestDto) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizId);

        // 의견 생성
        Comment comment = commentService.saveOne(requestDto, quiz, member);

        // 응답 객체 생성 및 반환
        return ResponseEntity.created(URI.create("v1/quizzes/{quizId}/comments")).body(CommentResponseDto.of(comment, member));
    }

    @Operation(summary = "의견 조회 API", description = "문제에 달린 의견들을 생성시간 역순으로 조회한다.")
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @GetMapping("v1/quizzes/{quizId}/comments")
    public ResponseEntity<List<CommentResponseDto>> readComment(@RequestHeader(value = "code") String code,
                                                                @PathVariable(value = "quizId") Long quizId) {
        // 회원 객체 조회 및 인가 체크
        Member member = memberService.findOne(code);

        // 문제 조회
        Quiz quiz = quizService.findOne(quizId);

        // 의견 생성
        List<Comment> comments = commentService.findComments(quiz);

        // 응답 객체 생성 및 반환
        return ResponseEntity.ok(comments.stream().map(comment -> CommentResponseDto.of(comment, member)).collect(Collectors.toList()));
    }

    @Operation(summary = "의견 삭제 API", description = "문제에 달린 의견을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO_CONTENT"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 회원입니다.}"))),
            @ApiResponse(responseCode = "404", description = "DATA NOT FOUND", content = @Content(schema = @Schema(example = "{message: 존재하지 않는 의견입니다.}")))
    })
    @Parameters({
            @Parameter(name = "code", description = "로그인한 회원 코드", required = true, in = ParameterIn.HEADER, example = "1234"),
            @Parameter(name = "quizId", description = "문제 아이디", required = true, in = ParameterIn.PATH, example = "1")
    })
    @DeleteMapping("v1/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestHeader(value = "code") String code, @PathVariable(value = "commentId") Long commentId) {
        // 의견 삭제
        commentService.deleteOne(commentId, code);

        // 응답 객체 생성 및 반환
        return ResponseEntity.noContent().build();
    }
}
