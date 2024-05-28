package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vd5Base {
    private String id;
    private Integer tanggal;
    private Integer bulan;
    private Integer tahun;
    private String kodePe;
    private String kodeAkun;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;
}
