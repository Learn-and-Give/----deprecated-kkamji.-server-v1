package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Chapter;
import com.kkamjidot.api.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "챕터 조회 응답 Dto")
public class ChapterResponseDto {
    @Schema(description = "챕터 아이디", example = "1", required = true)
    private final Long chapterId;

    @Schema(description = "챕터명", example = "1", required = true)
    private final String chapterName;

    @Schema(description = "시작일시", example = "1", required = true)
    private final Instant chapterStartDate;

    @Schema(description = "종료일시", example = "1", required = true)
    private final Instant chapterEndDate;

    @Schema(description = "열람 가능 여부", example = "1", required = true)
    private Boolean isReadable;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    public static ChapterResponseDto of(Chapter chapter, Member member) {
        return ChapterResponseDto.builder()
                .chapterId(chapter.getId())
                .chapterName(chapter.getChapterName())
                .chapterStartDate(chapter.getChapterStartDate())
                .chapterEndDate(chapter.getChapterEndDate())
                .isReadable(chapter.isReadable(member))
                .createdDate(chapter.getCreatedDate())
                .modifiedDate(chapter.getModifiedDate())
                .build();
    }
}
