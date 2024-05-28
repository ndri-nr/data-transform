package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510eDto extends Vd5Base {
    private String kodeEfek;
    private double volume;
    private double harga;
    private double nilaiPasarWajar;
}
