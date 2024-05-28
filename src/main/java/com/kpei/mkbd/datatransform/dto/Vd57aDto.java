package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd57aDto extends Vd5Base {
    private double saldo;
    private double terafiliasi;
    private double tidakTerafiliasi;
}
