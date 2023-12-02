package com.muratalarcin.mealexpress.data.entity;

import java.util.List;

public class CRUDCevap {
    private List<Sepet> sepetListesi;
    private int success;
    private String message;

    public CRUDCevap() {
    }

    public CRUDCevap(List<Sepet> sepetListesi, int success, String message) {
        this.sepetListesi = sepetListesi;
        this.success = success;
        this.message = message;
    }

    public List<Sepet> getSepetListesi() {
        return sepetListesi;
    }

    public void setSepetListesi(List<Sepet> sepetListesi) {
        this.sepetListesi = sepetListesi;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
