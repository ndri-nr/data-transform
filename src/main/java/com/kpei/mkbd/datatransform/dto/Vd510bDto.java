package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510bDto extends Vd5Base {
    private String kodeEfek;
    private String penjual;
    private Date tglPembelian;
    private Date tglPenjualan;
    private double nilaiPembelian;
    private double nilaiPenjualan;
    private String kodeEfekKolateral;
    private double jmlJaminan;
    private double nilaiPasarWajar;
    private double nilaiRankingLiabilities;
}
