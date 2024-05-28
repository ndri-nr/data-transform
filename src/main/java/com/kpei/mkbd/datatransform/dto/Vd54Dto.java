package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd54Dto extends Vd5Base {
    private String kodeJenisReksadana;
    private String namaJenisReksadana;
    private boolean isAfiliasi;
    private BigDecimal nilaiAktivaBersihUnit;
    private BigDecimal nilaiAktivaBersihReksadana;
    private BigDecimal batasanMkbd;
    private BigDecimal kelebihanMkbd;
}
