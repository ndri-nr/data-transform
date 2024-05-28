package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510bDto extends Vd5Base {
    private String kodeEfek;
    private String penjual;
    private Date tanggalPembelian;
    private Date tanggalPenjualan;
    private BigDecimal nilaiPembelian;
    private BigDecimal nilaiPenjualan;
    private String kodeEfekKolateral;
    private BigDecimal jumlahJaminan;
    private BigDecimal nilaiPasarWajar;
    private BigDecimal nilaiRankingLiabilitas;
}
