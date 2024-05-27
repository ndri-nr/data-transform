package com.kpei.mkbd.datatransform.dto;

public class Vd510eDto extends Vd5Base {
    private String kodeEfek;
    private double volume;
    private double harga;
    private double nilaiPasarWajar;

    public Vd510eDto() {
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getNilaiPasarWajar() {
        return nilaiPasarWajar;
    }

    public void setNilaiPasarWajar(double nilaiPasarWajar) {
        this.nilaiPasarWajar = nilaiPasarWajar;
    }
}
