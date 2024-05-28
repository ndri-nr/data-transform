package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510eDto extends Vd5Base {
    private String kodeEfek;
    private BigDecimal volume;
    private BigDecimal harga;
    private BigDecimal nilaiPasarWajar;
}
