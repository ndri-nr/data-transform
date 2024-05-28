package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vd5Base {
    private String id;
    private int tanggal;
    private int bulan;
    private int tahun;
    private String kodePe;
    private String kodeAkun;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;
}
