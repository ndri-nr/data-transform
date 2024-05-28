package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510hDto extends Vd5Base {
    private Date tglTransaksi;
    private double rincianBelanja;
    private Date tglRealisasi;
    private double komitmenTerealisasi;
    private double komitmenBelumTerealisasi;
    private double nilaiRankingLiabilities;
}
