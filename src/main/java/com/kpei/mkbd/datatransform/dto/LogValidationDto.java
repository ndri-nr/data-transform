package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogValidationDto {
    private String kodePe;
    private String kodeAkun;
    private String fileName;
    private String detailDescription;
    private String messageDescription;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
