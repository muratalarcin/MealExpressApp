package com.muratalarcin.mealexpress.data.entity;

public class YemekAdetBilgisi {
    private String yemekAdi;
    private int toplamAdet;
    private String yemekResimAdi;

    public YemekAdetBilgisi(String yemekAdi, int toplamAdet, String yemekResimAdi) {
        this.yemekAdi = yemekAdi;
        this.toplamAdet = toplamAdet;
        this.yemekResimAdi = yemekResimAdi;
    }

    public String getYemekAdi() {
        return yemekAdi;
    }

    public void setYemekAdi(String yemekAdi) {
        this.yemekAdi = yemekAdi;
    }

    public int getToplamAdet() {
        return toplamAdet;
    }

    public void setToplamAdet(int toplamAdet) {
        this.toplamAdet = toplamAdet;
    }

    public String getYemekResimAdi() {
        return yemekResimAdi;
    }

    public void setYemekResimAdi(String yemekResimAdi) {
        this.yemekResimAdi = yemekResimAdi;
    }
}
