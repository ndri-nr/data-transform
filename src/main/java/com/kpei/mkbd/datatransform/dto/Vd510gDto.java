package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510gDto extends Vd5Base {
    private Date tanggalKontrak;
    private String terafiliasi;
    private String pihakDijamin;
    private String rincianPenjaminan;
    private String jangkaWaktuPnejaminan;
    private Date tanggalBerakhir;
    private BigDecimal nilaiPenjaminan;
    private BigDecimal nilaiRankingLiabilitas;
}
