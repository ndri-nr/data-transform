package com.kpei.mkbd.datatransform.dto;

import java.sql.Date;

public class Vd510gDto extends Vd5Base {
    private Date tglKontrak;
    private String pihakDijamin;
    private boolean isTerafiliasi;
    private String rincianPenjaminan;
    private double jangkaWaktuPenjaminan;
    private Date tglBerakhir;
    private double nilaiPenjaminan;
    private double nilaiRankingLiabilities;

    public Vd510gDto() {
    }

    public Date getTglKontrak() {
        return tglKontrak;
    }

    public void setTglKontrak(Date tglKontrak) {
        this.tglKontrak = tglKontrak;
    }

    public String getPihakDijamin() {
        return pihakDijamin;
    }

    public void setPihakDijamin(String pihakDijamin) {
        this.pihakDijamin = pihakDijamin;
    }

    public boolean isTerafiliasi() {
        return isTerafiliasi;
    }

    public void setTerafiliasi(boolean terafiliasi) {
        isTerafiliasi = terafiliasi;
    }

    public String getRincianPenjaminan() {
        return rincianPenjaminan;
    }

    public void setRincianPenjaminan(String rincianPenjaminan) {
        this.rincianPenjaminan = rincianPenjaminan;
    }

    public double getJangkaWaktuPenjaminan() {
        return jangkaWaktuPenjaminan;
    }

    public void setJangkaWaktuPenjaminan(double jangkaWaktuPenjaminan) {
        this.jangkaWaktuPenjaminan = jangkaWaktuPenjaminan;
    }

    public Date getTglBerakhir() {
        return tglBerakhir;
    }

    public void setTglBerakhir(Date tglBerakhir) {
        this.tglBerakhir = tglBerakhir;
    }

    public double getNilaiPenjaminan() {
        return nilaiPenjaminan;
    }

    public void setNilaiPenjaminan(double nilaiPenjaminan) {
        this.nilaiPenjaminan = nilaiPenjaminan;
    }

    public double getNilaiRankingLiabilities() {
        return nilaiRankingLiabilities;
    }

    public void setNilaiRankingLiabilities(double nilaiRankingLiabilities) {
        this.nilaiRankingLiabilities = nilaiRankingLiabilities;
    }
}
