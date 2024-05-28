package com.kpei.mkbd.datatransform.dto;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vd510fDto extends Vd5Base {
    private Date tglKontrak;
    private String jnsPenjaminan;
    private String pihakDijamin;
    private String statusPenjaminan;
    private double nilaiKomitmenPenjaminan;
    private double haircutAtasEfek;
    private double nilaiBelumTerserap;
    private double nilaiBankGaransi;
    private double rankingLiabilities;
}
