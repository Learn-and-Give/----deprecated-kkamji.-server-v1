package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.enumerate.QuizCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "문제집 상세 내용 안에 들어가는 문제 개요 모음 응답 Dto")
public class QuizSummaryResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private Long quizId;

    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
    private String quizTitle;

    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
    private QuizCategory quizCategory;

    @Schema(description = "풀었는지 여부", example = "true", required = true)
    private Boolean quizIsSolved;

    public QuizSummaryResponseDto(Quiz quiz) {
        this.quizId = quiz.getId();
        this.quizTitle = quiz.getQuizTitle();
        this.quizCategory = quiz.getQuizCategory();
        this.quizIsSolved = false;
    }

    public void solveQuiz() {
        this.quizIsSolved = true;
    }
}
