package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510cDto extends Vd5Base {
    private String kodeEfek;
    private String terafiliasi;
    private String lembarNominal;
    private double hargaPerolehan;
    private double hargaPasarWajar;
    private double jmlJaminan;
    private String grupEmiten;
    private double persentaseNilaiPasar;
    private double nilaiRankingLiabilities;
}
