package com.kpei.mkbd.datatransform.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormulaAkun {
    private String tabel;
    private String kodeAkun;
    private String kdFormula;
    private String kolom;
}
