package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quizbook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

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

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    public static QuizbookResponseDto of(Quizbook quizbook) {
        return QuizbookResponseDto.builder()
                .quizbookId(quizbook.getId())
                .quizbookTitle(quizbook.getQuizbookTitle())
                .quizbookDescription(quizbook.getQuizbookDescription())
                .numOfQuizzes(quizbook.getNumberOfQuizzes())
                .submitUserName(quizbook.getQuizbookMemberName())
                .createdDate(quizbook.getCreatedDate())
                .modifiedDate(quizbook.getModifiedDate()).build();
    }
}
