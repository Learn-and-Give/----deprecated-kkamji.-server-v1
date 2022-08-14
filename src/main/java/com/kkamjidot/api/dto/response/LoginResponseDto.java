package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "로그인 응답 Dto")
public class LoginResponseDto {
    @Schema(description = "회원 ID", example = "1", required = true)
    private final Long userId;

    @Schema(description = "이름", example = "홍길동", required = true)
    private final String name;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;

    public static LoginResponseDto of(final Member member) {
        return LoginResponseDto.builder()
                .userId(member.getId())
                .name(member.getMemberName())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate()).build();
    }
}
