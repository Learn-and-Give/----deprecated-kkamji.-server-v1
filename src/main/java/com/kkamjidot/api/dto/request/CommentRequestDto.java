package com.kkamjidot.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(name = "의견 생성 요청 Dto")
@NoArgsConstructor
@Getter
public class CommentRequestDto {
    @Schema(description = "의견 내용", example = "의견 내용", required = true)
    @NotNull(message = "의견 내용은 필수 입력 값입니다.")
    private String cmtContent;
}
