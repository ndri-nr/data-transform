package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510aDto extends Vd5Base {
    private String kodeEfek;
    private String pembeli;
    private Date tglPenjualan;
    private Date tglPembelian;
    private double nilaiPenjualan;
    private double nilaiPembelian;
    private String kodeEfekKolateral;
    private double jmlJaminan;
    private double nilaiPasarWajar;
    private double nilaiRankingLiabilities;
}
