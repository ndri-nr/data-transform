package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd57bDto extends Vd5Base {
    private double saldo;
    private double dimiliki;
    private double dipisahkan;
    private double tidakDipisahkan;
}
