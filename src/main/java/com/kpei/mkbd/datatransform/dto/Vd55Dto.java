package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd55Dto extends Vd5Base {
    private String kodeEfek;
    private Double nilaiEfek;
    private String kodeEfekLn;
    private Double nilaiEfekLn;
    private Double nilaiEfekTutupLn;
    private Double nilaiHaircutTutupLn;
    private Double nilaiHaircutLn;
    private Double jmlPengembalianHaircut;
}
