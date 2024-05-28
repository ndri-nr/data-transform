package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd55Dto extends Vd5Base {
    private String kodeEfek;
    private double nilaiEfek;
    private String kodeEfekLN;
    private double nilaiEfekLN;
    private double nilaiEfekTutupLN;
    private double nilaiHaircutTutupLN;
    private double nilaiHaircutLN;
    private double jmlPengembalianHaircut;
}
