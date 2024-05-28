package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd56aDto extends Vd5Base {
    private BigDecimal saldo;
    private BigDecimal terafiliasi;
    private BigDecimal tidakTerafiliasi;
}
