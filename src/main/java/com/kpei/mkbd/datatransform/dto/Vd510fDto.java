package com.kpei.mkbd.datatransform.dto;

import java.sql.Date;

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

    public Vd510fDto() {
    }

    public Date getTglKontrak() {
        return tglKontrak;
    }

    public void setTglKontrak(Date tglKontrak) {
        this.tglKontrak = tglKontrak;
    }

    public String getJnsPenjaminan() {
        return jnsPenjaminan;
    }

    public void setJnsPenjaminan(String jnsPenjaminan) {
        this.jnsPenjaminan = jnsPenjaminan;
    }

    public String getPihakDijamin() {
        return pihakDijamin;
    }

    public void setPihakDijamin(String pihakDijamin) {
        this.pihakDijamin = pihakDijamin;
    }

    public String getStatusPenjaminan() {
        return statusPenjaminan;
    }

    public void setStatusPenjaminan(String statusPenjaminan) {
        this.statusPenjaminan = statusPenjaminan;
    }

    public double getNilaiKomitmenPenjaminan() {
        return nilaiKomitmenPenjaminan;
    }

    public void setNilaiKomitmenPenjaminan(double nilaiKomitmenPenjaminan) {
        this.nilaiKomitmenPenjaminan = nilaiKomitmenPenjaminan;
    }

    public double getHaircutAtasEfek() {
        return haircutAtasEfek;
    }

    public void setHaircutAtasEfek(double haircutAtasEfek) {
        this.haircutAtasEfek = haircutAtasEfek;
    }

    public double getNilaiBelumTerserap() {
        return nilaiBelumTerserap;
    }

    public void setNilaiBelumTerserap(double nilaiBelumTerserap) {
        this.nilaiBelumTerserap = nilaiBelumTerserap;
    }

    public double getNilaiBankGaransi() {
        return nilaiBankGaransi;
    }

    public void setNilaiBankGaransi(double nilaiBankGaransi) {
        this.nilaiBankGaransi = nilaiBankGaransi;
    }

    public double getRankingLiabilities() {
        return rankingLiabilities;
    }

    public void setRankingLiabilities(double rankingLiabilities) {
        this.rankingLiabilities = rankingLiabilities;
    }
}
