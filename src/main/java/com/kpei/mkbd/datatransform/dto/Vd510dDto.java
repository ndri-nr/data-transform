package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510dDto extends Vd5Base {
    private String namaNasabah;
    private String marginSelling;
    private BigDecimal nilaiPembiayaan;
    private BigDecimal nilaiJaminan;
    private BigDecimal rasioPembiayaan;
    private BigDecimal nilaiRankingLiabilitiesNasabah;
    private BigDecimal nilaiRankingLiabilitiesRasio;
}
