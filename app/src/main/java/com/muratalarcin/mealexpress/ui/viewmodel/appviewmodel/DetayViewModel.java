package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public YemeklerDaoRepository yrepo;
    private MutableLiveData<Integer> siparisAdet = new MutableLiveData<>();
    private MutableLiveData<Double> toplamFiyat = new MutableLiveData<>();
    private Yemekler gelenYemek;

    @Inject
    public DetayViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        yemekleriYukle();
    }

    // init metodu veya constructor içinde başlatma işlemlerini yapabilirsiniz
    public void init(Yemekler yemek) {
        gelenYemek = yemek;
        siparisAdet.setValue(yemek.getYemek_siparis_adet());
        toplamFiyat.setValue((double) (yemek.getYemek_fiyat() * yemek.getYemek_siparis_adet()));
    }

    public LiveData<Integer> getSiparisAdet() {
        return siparisAdet;
    }

    public LiveData<Double> getToplamFiyat() {
        return toplamFiyat;
    }

    public void artirAdet() {
        int mevcutAdet = siparisAdet.getValue();
        int yeniAdet = mevcutAdet + 1;
        siparisAdet.setValue(yeniAdet);
        toplamFiyat.setValue((double) (gelenYemek.getYemek_fiyat() * yeniAdet));
    }

    public void azaltAdet() {
        int mevcutAdet = siparisAdet.getValue();
        if (mevcutAdet > 0) {
            int yeniAdet = mevcutAdet - 1;
            siparisAdet.setValue(yeniAdet);
            toplamFiyat.setValue((double) (gelenYemek.getYemek_fiyat() * yeniAdet));
        }
    }

    public void yemekleriYukle() {
        yrepo.yemekleriYukle();
    }

    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }
}
