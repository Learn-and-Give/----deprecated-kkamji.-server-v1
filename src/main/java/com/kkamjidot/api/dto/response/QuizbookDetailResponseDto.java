package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Quizbook;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(name = "문제집 상세 내용 조회 응답 Dto")
public class QuizbookDetailResponseDto extends QuizbookResponseDto {
    @Schema(description = "문제집에 포함된 문제의 문제 개요들", required = true)
    private List<QuizSummaryResponseDto> quizSummaries = new ArrayList<>();
    public QuizbookDetailResponseDto(Quizbook quizbook) {
        super(quizbook);
    }
}
