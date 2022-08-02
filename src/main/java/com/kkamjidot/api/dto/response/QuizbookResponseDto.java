package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(name = "문제집 상세 내용 응답 Dto")
public class QuizbookResponseDto {
    @Schema(description = "문제집 ID", example = "1", required = true)
    private Long quizbookId;

    @Schema(description = "문제집 제목", example = "문제집1 제목", required = true)
    private String quizbookTitle;

    @Schema(description = "문제집 설명", example = "문제집1 설명", required = true)
    private String quizbookDescription;

    @Schema(description = "문제집 주차", example = "1", required = true)
    private Integer quizbookWeek;

    @Schema(description = "문제 수", example = "10", required = true)
    private Integer numOfQuizzes;

    @Schema(description = "제출 회원 이름", example = "홍길동", required = true)
    private String submitUserName;

    @Schema(description = "문제 아이디", example = "1", required = true)
    private Long quizId;

    private List<QuizInQuizbookResponseDto> quizzes = new ArrayList<>();
}
