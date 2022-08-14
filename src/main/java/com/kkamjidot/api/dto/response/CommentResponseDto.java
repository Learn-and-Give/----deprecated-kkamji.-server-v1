package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "의견 응답 Dto")
public class CommentResponseDto  implements Serializable {
    @Schema(description = "의견 아이디", example = "1", required = true)
    private final Long commentId;

    @Schema(description = "의견 작성자 이름", example = "홍길동", required = true)
    private final String commentUserName;

    @Schema(description = "의견 내용", example = "의견 내용", required = true)
    private final String commentContent;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .commentUserName(comment.getMember().getMemberName())
                .commentContent(comment.getCmtContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }
}
