package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd55Dto extends Vd5Base {
    private String kodeEfek;
    private BigDecimal nilaiEfek;
    private String kodeEfekLn;
    private BigDecimal nilaiEfekLn;
    private BigDecimal nilaiEfekTutupLn;
    private BigDecimal nilaiHaircutTutupLn;
    private BigDecimal nilaiHaircutLn;
    private BigDecimal jmlPengembalianHaircut;
}
