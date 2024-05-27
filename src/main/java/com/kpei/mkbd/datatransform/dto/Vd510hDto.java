package com.kpei.mkbd.datatransform.dto;

import java.sql.Date;

public class Vd510hDto extends Vd5Base {
    private Date tglTransaksi;
    private double rincianBelanja;
    private Date tglRealisasi;
    private double komitmenTerealisasi;
    private double komitmenBelumTerealisasi;
    private double nilaiRankingLiabilities;

    public Vd510hDto() {
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public double getRincianBelanja() {
        return rincianBelanja;
    }

    public void setRincianBelanja(double rincianBelanja) {
        this.rincianBelanja = rincianBelanja;
    }

    public Date getTglRealisasi() {
        return tglRealisasi;
    }

    public void setTglRealisasi(Date tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }

    public double getKomitmenTerealisasi() {
        return komitmenTerealisasi;
    }

    public void setKomitmenTerealisasi(double komitmenTerealisasi) {
        this.komitmenTerealisasi = komitmenTerealisasi;
    }

    public double getKomitmenBelumTerealisasi() {
        return komitmenBelumTerealisasi;
    }

    public void setKomitmenBelumTerealisasi(double komitmenBelumTerealisasi) {
        this.komitmenBelumTerealisasi = komitmenBelumTerealisasi;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }
}
