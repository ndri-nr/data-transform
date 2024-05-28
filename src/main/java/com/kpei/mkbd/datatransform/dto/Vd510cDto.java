package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510cDto extends Vd5Base {
    private String kodeEfek;
    private String terafiliasi;
    private String grupEmiten;
    private String persentaseNilaiPasar;
    private BigDecimal lembarNominal;
    private BigDecimal hargaPerolehan;
    private BigDecimal hargaPasarWajar;
    private BigDecimal nilaiPasarWajar;
    private BigDecimal nilaiRankingLiabilitas;
}
