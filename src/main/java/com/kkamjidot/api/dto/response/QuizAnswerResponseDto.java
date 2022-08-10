package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "퀴즈 정답 및 해설 조회 응답 Dto")
public class QuizAnswerResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private final Long quizId;

    @Schema(description = "문제 정답", example = "문제1 정답", required = true)
    private final String quizAnswer;

    @Schema(description = "문제 해설", example = "문제1 해설", required = true)
    private final String quizExplanation;

    @Schema(description = "문제 출처", example = "문제1 출처", required = true)
    private final String quizSource;

    public static QuizAnswerResponseDto of(Quiz quiz) {
        return QuizAnswerResponseDto.builder()
                .quizId(quiz.getId())
                .quizAnswer(quiz.getQuizAnswer())
                .quizExplanation(quiz.getQuizExplanation())
                .quizSource(quiz.getQuizSource())
                .build();
    }
}
