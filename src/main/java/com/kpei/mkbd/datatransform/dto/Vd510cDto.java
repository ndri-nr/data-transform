package com.kpei.mkbd.datatransform.dto;

public class Vd510cDto extends Vd5Base {
    private String kodeEfek;
    private boolean isTerafiliasi;
    private String lembarNominal;
    private double hargaPerolehan;
    private double hargaPasarWajar;
    private double jmlJaminan;
    private String grupEmiten;
    private double persentaseNilaiPasar;
    private double nilaiRankingLiabilities;

    public Vd510cDto() {
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public boolean isTerafiliasi() {
        return isTerafiliasi;
    }

    public void setTerafiliasi(boolean terafiliasi) {
        isTerafiliasi = terafiliasi;
    }

    public String getLembarNominal() {
        return lembarNominal;
    }

    public void setLembarNominal(String lembarNominal) {
        this.lembarNominal = lembarNominal;
    }

    public double getHargaPerolehan() {
        return hargaPerolehan;
    }

    public void setHargaPerolehan(double hargaPerolehan) {
        this.hargaPerolehan = hargaPerolehan;
    }

    public double getHargaPasarWajar() {
        return hargaPasarWajar;
    }

    public void setHargaPasarWajar(double hargaPasarWajar) {
        this.hargaPasarWajar = hargaPasarWajar;
    }

    public double getJmlJaminan() {
        return jmlJaminan;
    }

    public void setJmlJaminan(double jmlJaminan) {
        this.jmlJaminan = jmlJaminan;
    }

    public String getGrupEmiten() {
        return grupEmiten;
    }

    public void setGrupEmiten(String grupEmiten) {
        this.grupEmiten = grupEmiten;
    }

    public double getPersentaseNilaiPasar() {
        return persentaseNilaiPasar;
    }

    public void setPersentaseNilaiPasar(double persentaseNilaiPasar) {
        this.persentaseNilaiPasar = persentaseNilaiPasar;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }
}
