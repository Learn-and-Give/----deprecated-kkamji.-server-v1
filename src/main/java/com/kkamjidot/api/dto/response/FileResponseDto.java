package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "파일 응답 Dto")
public class FileResponseDto {
    @Schema(description = "파일명", example = "true", required = true)
    private final String fileName;

    @Schema(description = "파일 경로", example = "true", required = true)
    private final String filePath;

    public static FileResponseDto of(File file) {
        return new FileResponseDto(file.getFileName(), file.getFilePath());
    }
}
