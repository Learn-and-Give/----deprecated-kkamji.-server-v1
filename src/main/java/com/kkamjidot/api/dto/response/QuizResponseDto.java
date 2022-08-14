package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.enumerate.QuizCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Schema(name = "문제 내용 조회 응답 Dto")
public class QuizResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private final Long quizId;

    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
    private final String quizTitle;

    @Schema(description = "문제 내용", example = "문제1 내용", required = true)
    private final String quizContent;

    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
    private final QuizCategory quizCategory;

    @Schema(description = "문제집 내 문제 번호", example = "1", required = true)
    private final Integer quizNumber;

    @Schema(description = "풀었는지 여부", example = "true", required = true)
    private final Boolean isQuizSolved;

    @Schema(description = "내가 작성자인지 여부", example = "true", required = true)
    private final Boolean isMine;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    @Schema(description = "파일", example = "true", required = true)
    private final List<FileResponseDto> files = new ArrayList<>();

    public static QuizResponseDto of(@NotNull Quiz quiz, Member member) {
        QuizResponseDto quizDto = QuizResponseDto.builder()
                .quizId(quiz.getId())
                .quizTitle(quiz.getQuizTitle())
                .quizContent(quiz.getQuizContent())
                .quizCategory(quiz.getQuizCategory())
                .quizNumber(quiz.getQuizNumber())
                .isQuizSolved(quiz.getIsSolved(member))
                .isMine(quiz.getIsMine(member))
                .createdDate(quiz.getCreatedDate())
                .modifiedDate(quiz.getModifiedDate())
                .build();
        quiz.getFiles().forEach(file -> quizDto.files.add(FileResponseDto.of(file)));

        return quizDto;
    }
}
