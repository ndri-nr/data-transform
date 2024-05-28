package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510hDto extends Vd5Base {
    private Date tanggalKomitmen;
    private String rincianBelanja;
    private Date tanggalRealisasi;
    private String rincianPenjaminan;
    private BigDecimal komitmenTerealisasi;
    private BigDecimal komitmenBelumTerealisasi;
    private BigDecimal nilaiRankingLiabilitas;
}
