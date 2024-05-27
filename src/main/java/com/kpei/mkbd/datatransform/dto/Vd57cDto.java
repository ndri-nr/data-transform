package com.kpei.mkbd.datatransform.dto;

public class Vd57cDto extends Vd5Base {
    private double saldo;
    private double lmHrKerja;
    private double dimiliki;
    private double dipisahkan;
    private String penjelasan;

    public Vd57cDto() {
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLmHrKerja() {
        return lmHrKerja;
    }

    public void setLmHrKerja(double lmHrKerja) {
        this.lmHrKerja = lmHrKerja;
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

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }
}
