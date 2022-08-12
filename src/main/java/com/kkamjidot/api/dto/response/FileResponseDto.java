package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "파일 응답 Dto")
public class FileResponseDto {
    @Schema(description = "파일명", example = "true", required = true)
    private final String fileName;

    @Schema(description = "파일 경로", example = "true", required = true)
    private final String filePath;

    @Schema(description = "생성일시", example = "2022-08-11T16:47:25", required = true)
    private LocalDateTime createdDate;

    @Schema(description = "수정일시", example = "null", required = true)
    private LocalDateTime modifiedDate;
    public static FileResponseDto of(File file) {
        return FileResponseDto.builder()
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .createdDate(file.getCreatedDate())
                .modifiedDate(file.getModifiedDate()).build();
    }
}
