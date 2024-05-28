package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd57cDto extends Vd5Base {
    private double saldo;
    private double lmHrKerja;
    private double dimiliki;
    private double dipisahkan;
    private String penjelasan;
}
