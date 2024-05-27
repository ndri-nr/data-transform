package com.kpei.mkbd.datatransform.dto;

public class Vd56bDto extends Vd5Base {
    private double saldo;
    private double dimiliki;
    private double dipisahkan;
    private double tidakDipisahkan;

    public Vd56bDto() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getDimiliki() {
        return dimiliki;
    }

    public void setDimiliki(double dimiliki) {
        this.dimiliki = dimiliki;
    }

    public double getDipisahkan() {
        return dipisahkan;
    }

    public void setDipisahkan(double dipisahkan) {
        this.dipisahkan = dipisahkan;
    }

    public double getTidakDipisahkan() {
        return tidakDipisahkan;
    }

    public void setTidakDipisahkan(double tidakDipisahkan) {
        this.tidakDipisahkan = tidakDipisahkan;
    }
}
