package com.kpei.mkbd.datatransform.dto;

public class Vd510iDto extends Vd5Base {
    private String jnsTransaksi;
    private String jnsMataUang;
    private double transaksi;
    private double untungRugiTerealisasi;
    private double nilaiRankingLiabilities;

    public Vd510iDto() {
    }

    public String getJnsTransaksi() {
        return jnsTransaksi;
    }

    public void setJnsTransaksi(String jnsTransaksi) {
        this.jnsTransaksi = jnsTransaksi;
    }

    public String getJnsMataUang() {
        return jnsMataUang;
    }

    public void setJnsMataUang(String jnsMataUang) {
        this.jnsMataUang = jnsMataUang;
    }

    public double getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(double transaksi) {
        this.transaksi = transaksi;
    }

    public double getUntungRugiTerealisasi() {
        return untungRugiTerealisasi;
    }

    public void setUntungRugiTerealisasi(double untungRugiTerealisasi) {
        this.untungRugiTerealisasi = untungRugiTerealisasi;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }
}
