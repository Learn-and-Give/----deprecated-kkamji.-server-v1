package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.enumerate.QuizCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "문제집 상세 내용 안에 들어가는 문제 개요 모음 응답 Dto")
public class QuizSummaryResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private final Long quizId;

    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
    private final String quizTitle;

    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
    private final QuizCategory quizCategory;

    @Schema(description = "풀었는지 여부", example = "true", required = true)
    private final Boolean isQuizSolved;

    @Schema(description = "문제집 내 문제 번호", example = "1", required = true)
    private final Integer quizNumber;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    public static QuizSummaryResponseDto of(Quiz quiz, Member member) {
        return QuizSummaryResponseDto.builder()
                .quizId(quiz.getId())
                .quizTitle(quiz.getQuizTitle())
                .quizCategory(quiz.getQuizCategory())
                .isQuizSolved(quiz.getIsSolved(member))
                .quizNumber(quiz.getQuizNumber())
                .createdDate(quiz.getCreatedDate())
                .modifiedDate(quiz.getModifiedDate()).build();
    }
}
