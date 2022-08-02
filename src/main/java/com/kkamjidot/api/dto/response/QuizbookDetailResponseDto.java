package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quizbook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(name = "문제집 상세 내용 조회 응답 Dto")
public class QuizbookDetailResponseDto extends QuizbookResponseDto {
    @Schema(description = "포함된 문제들", required = true)
    private List<QuizInQuizbookResponseDto> quizzes = new ArrayList<>();
    public QuizbookDetailResponseDto(Quizbook quizbook) {
        super(quizbook);
    }
}
