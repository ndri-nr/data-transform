package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd56bDto extends Vd5Base {
    private BigDecimal saldo;
    private BigDecimal dimiliki;
    private BigDecimal dipisahkan;
    private BigDecimal tidakDipisahkan;
}
