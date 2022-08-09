package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quizbook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@Schema(name = "주차별 문제집 모음 조회 응답 Dto")
public class QuizbookResponseDto {
    @Schema(description = "문제집 ID", example = "1", required = true)
    private final Long quizbookId;

    @Schema(description = "문제집 제목", example = "문제집1 제목", required = true)
    private final String quizbookTitle;

    @Schema(description = "문제집 설명", example = "문제집1 설명", required = true)
    private final String quizbookDescription;

    @Schema(description = "문제 수", example = "10", required = true)
    private final Integer numOfQuizzes;

    @Schema(description = "제출 회원 이름", example = "홍길동", required = true)
    private final String submitUserName;
}
