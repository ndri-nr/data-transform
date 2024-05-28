package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510fDto extends Vd5Base {
    private Date tanggalKontrak;
    private String jenisPenjaminan;
    private String pihakDijamin;
    private String statuPenjaminan;
    private BigDecimal nilaiKomitmenPenjaminan;
    private BigDecimal haircutAtasEfek;
    private BigDecimal nilaiBelumTerserap;
    private BigDecimal nilaiBankGaransi;
    private BigDecimal nilaiRankingLiabilitas;
}
