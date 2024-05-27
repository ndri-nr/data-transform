package com.kpei.mkbd.datatransform.dto;

public class Vd57aDto extends Vd5Base {
    private double saldo;
    private double terafiliasi;
    private double tidakTerafiliasi;

    public Vd57aDto() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTerafiliasi() {
        return terafiliasi;
    }

    public void setTerafiliasi(double terafiliasi) {
        this.terafiliasi = terafiliasi;
    }

    public double getTidakTerafiliasi() {
        return tidakTerafiliasi;
    }

    public void setTidakTerafiliasi(double tidakTerafiliasi) {
        this.tidakTerafiliasi = tidakTerafiliasi;
    }
}
