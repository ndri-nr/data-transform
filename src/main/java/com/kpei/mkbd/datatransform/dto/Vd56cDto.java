package com.kpei.mkbd.datatransform.dto;

public class Vd56cDto extends Vd5Base {

    private String kodeBank;
    private boolean isSendiriNasabah;
    private String noRekening;
    private String kodeCurrency;
    private double saldo;
    private double saldoRupiah;
    private String penjelasan;

    public Vd56cDto() {
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public boolean isSendiriNasabah() {
        return isSendiriNasabah;
    }

    public void setSendiriNasabah(boolean sendiriNasabah) {
        isSendiriNasabah = sendiriNasabah;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getKodeCurrency() {
        return kodeCurrency;
    }

    public void setKodeCurrency(String kodeCurrency) {
        this.kodeCurrency = kodeCurrency;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldoRupiah() {
        return saldoRupiah;
    }

    public void setSaldoRupiah(double saldoRupiah) {
        this.saldoRupiah = saldoRupiah;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }
}
