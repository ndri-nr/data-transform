package com.kpei.mkbd.datatransform.dto;

import java.sql.Date;

public class Vd510aDto extends Vd5Base {
    private String kodeEfek;
    private String pembeli;
    private Date tglPenjualan;
    private Date tglPembelian;
    private double nilaiPenjualan;
    private double nilaiPembelian;
    private String kodeEfekKolateral;
    private double jmlJaminan;
    private double nilaiPasarWajar;
    private double nilaiRankingLiabilities;

    public Vd510aDto() {
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public Date getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public Date getTglPembelian() {
        return tglPembelian;
    }

    public void setTglPembelian(Date tglPembelian) {
        this.tglPembelian = tglPembelian;
    }

    public double getNilaiPenjualan() {
        return nilaiPenjualan;
    }

    public void setNilaiPenjualan(double nilaiPenjualan) {
        this.nilaiPenjualan = nilaiPenjualan;
    }

    public double getNilaiPembelian() {
        return nilaiPembelian;
    }

    public void setNilaiPembelian(double nilaiPembelian) {
        this.nilaiPembelian = nilaiPembelian;
    }

    public String getKodeEfekKolateral() {
        return kodeEfekKolateral;
    }

    public void setKodeEfekKolateral(String kodeEfekKolateral) {
        this.kodeEfekKolateral = kodeEfekKolateral;
    }

    public double getJmlJaminan() {
        return jmlJaminan;
    }

    public void setJmlJaminan(double jmlJaminan) {
        this.jmlJaminan = jmlJaminan;
    }

    public double getNilaiPasarWajar() {
        return nilaiPasarWajar;
    }

    public void setNilaiPasarWajar(double nilaiPasarWajar) {
        this.nilaiPasarWajar = nilaiPasarWajar;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }
}
