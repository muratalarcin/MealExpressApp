package com.muratalarcin.mealexpress.data.entity;

public class YemekAdetBilgisi {
    private String yemekAdi;
    private String toplamAdet;
    private String yemekFiyat;
    private String yemekResimAdi;
    private String sepet_yemek_id;
    private String kullanici_adi;

    public YemekAdetBilgisi(String yemekAdi, String toplamAdet, String yemekFiyat, String yemekResimAdi, String sepet_yemek_id, String kullanici_adi) {
        this.yemekAdi = yemekAdi;
        this.toplamAdet = toplamAdet;
        this.yemekFiyat = yemekFiyat;
        this.yemekResimAdi = yemekResimAdi;
        this.sepet_yemek_id = sepet_yemek_id;
        this.kullanici_adi = kullanici_adi;
    }

    public String getYemekAdi() {
        return yemekAdi;
    }

    public void setYemekAdi(String yemekAdi) {
        this.yemekAdi = yemekAdi;
    }

    public String getToplamAdet() {
        return toplamAdet;
    }

    public void setToplamAdet(String toplamAdet) {
        this.toplamAdet = toplamAdet;
    }

    public String getYemekFiyat() {
        return yemekFiyat;
    }

    public void setYemekFiyat(String yemekFiyat) {
        this.yemekFiyat = yemekFiyat;
    }

    public String getYemekResimAdi() {
        return yemekResimAdi;
    }

    public void setYemekResimAdi(String yemekResimAdi) {
        this.yemekResimAdi = yemekResimAdi;
    }

    public String getSepet_yemek_id() {
        return sepet_yemek_id;
    }

    public void setSepet_yemek_id(String sepet_yemek_id) {
        this.sepet_yemek_id = sepet_yemek_id;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }
}


