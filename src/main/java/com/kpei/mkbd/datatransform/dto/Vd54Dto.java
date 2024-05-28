package com.kpei.mkbd.datatransform.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd54Dto extends Vd5Base {
    private String kodeJenisReksadana;
    private String namaReksadana;
    private boolean isAfiliasi;
    private double nilaiAktivaBersihUnit;
    private double nilaiAktivaBersihReksadana;
    private double batasaMkbd;
    private double kelebihanBatasan;
}
