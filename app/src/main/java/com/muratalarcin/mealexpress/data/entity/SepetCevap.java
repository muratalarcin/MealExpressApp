package com.muratalarcin.mealexpress.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SepetCevap {
    @SerializedName("sepet_yemekler")
    private List<SepetString> sepetListesi;
    private int success;

    public SepetCevap() {
    }

    public SepetCevap(List<SepetString> sepetListesi, int success) {
        this.sepetListesi = sepetListesi;
        this.success = success;
    }

    public List<SepetString> getSepetListesi() {
        return sepetListesi;
    }

    public void setSepetListesi(List<SepetString> sepetListesi) {
        this.sepetListesi = sepetListesi;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
