package com.kpei.mkbd.datatransform.dto;

public class Vd54Dto extends Vd5Base {
    private String kodeJenisReksadana;
    private String namaReksadana;
    private boolean isAfiliasi;
    private double nilaiAktivaBersihUnit;
    private double nilaiAktivaBersihReksadana;
    private double batasaMkbd;
    private double kelebihanBatasan;

    public Vd54Dto() {
    }

    public String getKodeJenisReksadana() {
        return kodeJenisReksadana;
    }

    public void setKodeJenisReksadana(String kodeJenisReksadana) {
        this.kodeJenisReksadana = kodeJenisReksadana;
    }

    public String getNamaReksadana() {
        return namaReksadana;
    }

    public void setNamaReksadana(String namaReksadana) {
        this.namaReksadana = namaReksadana;
    }

    public boolean isAfiliasi() {
        return isAfiliasi;
    }

    public void setAfiliasi(boolean afiliasi) {
        isAfiliasi = afiliasi;
    }

    public double getNilaiAktivaBersihUnit() {
        return nilaiAktivaBersihUnit;
    }

    public void setNilaiAktivaBersihUnit(double nilaiAktivaBersihUnit) {
        this.nilaiAktivaBersihUnit = nilaiAktivaBersihUnit;
    }

    public double getNilaiAktivaBersihReksadana() {
        return nilaiAktivaBersihReksadana;
    }

    public void setNilaiAktivaBersihReksadana(double nilaiAktivaBersihReksadana) {
        this.nilaiAktivaBersihReksadana = nilaiAktivaBersihReksadana;
    }

    public double getBatasaMkbd() {
        return batasaMkbd;
    }

    public void setBatasaMkbd(double batasaMkbd) {
        this.batasaMkbd = batasaMkbd;
    }

    public double getKelebihanBatasan() {
        return kelebihanBatasan;
    }

    public void setKelebihanBatasan(double kelebihanBatasan) {
        this.kelebihanBatasan = kelebihanBatasan;
    }
}
