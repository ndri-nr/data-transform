package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd59Dto extends Vd5Base {
    private BigDecimal jumlah;
    private BigDecimal total;
}
