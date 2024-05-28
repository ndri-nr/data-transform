package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd57cDto extends Vd5Base {
    private BigDecimal saldo;
    private BigDecimal lmHrKerja;
    private BigDecimal dimiliki;
    private BigDecimal dipisahkan;
    private String penjelasan;
}
