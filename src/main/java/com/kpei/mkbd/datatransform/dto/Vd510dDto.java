package com.kpei.mkbd.datatransform.dto;

public class Vd510dDto extends Vd5Base {
    private String namaNasabah;
    private double marginSelling;
    private double nilaiPembiayaan;
    private double nilaiJaminan;
    private double rasioPembiayaan;
    private double nilaiRankingLiabilities;
    private double nilaiRankingLiabilitiesRasio;

    public Vd510dDto() {
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public double getMarginSelling() {
        return marginSelling;
    }

    public void setMarginSelling(double marginSelling) {
        this.marginSelling = marginSelling;
    }

    public double getNilaiPembiayaan() {
        return nilaiPembiayaan;
    }

    public void setNilaiPembiayaan(double nilaiPembiayaan) {
        this.nilaiPembiayaan = nilaiPembiayaan;
    }

    public double getNilaiJaminan() {
        return nilaiJaminan;
    }

    public void setNilaiJaminan(double nilaiJaminan) {
        this.nilaiJaminan = nilaiJaminan;
    }

    public double getRasioPembiayaan() {
        return rasioPembiayaan;
    }

    public void setRasioPembiayaan(double rasioPembiayaan) {
        this.rasioPembiayaan = rasioPembiayaan;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }

    public double getNilaiRankingLiabilitiesRasio() {
        return nilaiRankingLiabilitiesRasio;
    }

    public void setNilaiRankingLiabilitiesRasio(double nilaiRankingLiabilitiesRasio) {
        this.nilaiRankingLiabilitiesRasio = nilaiRankingLiabilitiesRasio;
    }
}
