package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510iDto extends Vd5Base {
    private String jnsTransaksi;
    private String jnsMataUang;
    private double transaksi;
    private double untungRugiTerealisasi;
    private double nilaiRankingLiabilities;
}
