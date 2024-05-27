package com.kpei.mkbd.datatransform.dto;

import java.sql.Date;

public class Vd510bDto extends Vd5Base {
    private String kodeEfek;
    private String penjual;
    private Date tglPembelian;
    private Date tglPenjualan;
    private double nilaiPembelian;
    private double nilaiPenjualan;
    private String kodeEfekKolateral;
    private double jmlJaminan;
    private double nilaiPasarWajar;
    private double nilaiRankingLiabilities;

    public Vd510bDto() {
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public Date getTglPembelian() {
        return tglPembelian;
    }

    public void setTglPembelian(Date tglPembelian) {
        this.tglPembelian = tglPembelian;
    }

    public Date getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public double getNilaiPembelian() {
        return nilaiPembelian;
    }

    public void setNilaiPembelian(double nilaiPembelian) {
        this.nilaiPembelian = nilaiPembelian;
    }

    public double getNilaiPenjualan() {
        return nilaiPenjualan;
    }

    public void setNilaiPenjualan(double nilaiPenjualan) {
        this.nilaiPenjualan = nilaiPenjualan;
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
