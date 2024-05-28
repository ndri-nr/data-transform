package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510dDto extends Vd5Base {
    private String namaNasabah;
    private double marginSelling;
    private double nilaiPembiayaan;
    private double nilaiJaminan;
    private double rasioPembiayaan;
    private double nilaiRankingLiabilities;
    private double nilaiRankingLiabilitiesRasio;
}
