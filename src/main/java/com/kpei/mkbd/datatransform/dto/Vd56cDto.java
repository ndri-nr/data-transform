package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd56cDto extends Vd5Base {
    private String kodeBank;
    private BigDecimal saldo;
    private Boolean isSendiriNasabah;
    private String noRekening;
    private String kodeCurrency;
    private BigDecimal saldoRupiah;
    private String penjelasan;
}
