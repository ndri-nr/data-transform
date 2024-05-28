package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510gDto extends Vd5Base {
    private Date tglKontrak;
    private String pihakDijamin;
    private boolean isTerafiliasi;
    private String rincianPenjaminan;
    private double jangkaWaktuPenjaminan;
    private Date tglBerakhir;
    private double nilaiPenjaminan;
    private double nilaiRankingLiabilities;
}
