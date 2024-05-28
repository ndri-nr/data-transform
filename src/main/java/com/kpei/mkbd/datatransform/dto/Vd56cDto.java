package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd56cDto extends Vd5Base {
    private String kodeBank;
    private boolean isSendiriNasabah;
    private String noRekening;
    private String kodeCurrency;
    private double saldo;
    private double saldoRupiah;
    private String penjelasan;
}
