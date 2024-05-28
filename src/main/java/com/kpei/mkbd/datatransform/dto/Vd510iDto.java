package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510iDto extends Vd5Base {
    private Date tanggalTransaksi;
    private String jenisTransaksi;
    private String jenisMataUang;
    private BigDecimal nilaiTransaksi;
    private BigDecimal untungRugiBelumTerealisasi;
    private BigDecimal nilaiRankingLiabilitas;
}
