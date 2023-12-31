package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    public YemeklerDaoRepository yrepo;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;

    @Inject
    public AnasayfaViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        yemekleriYukle();
        yemeklerListesi = yrepo.yemeklerListesi;
    }

    public void yemekleriYukle() {
        yrepo.yemekleriYukle();
    }

    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }


    public List<Yemekler> filtreleYemekler(List<Yemekler> yemeklerListesi, String s) {
        List<Yemekler> filtrelenmisListe = new ArrayList<>();
        for (Yemekler yemek : yemeklerListesi) {
            if (yemek.getYemek_adi().toLowerCase().contains(s.toLowerCase())) {
                filtrelenmisListe.add(yemek);
            }
        }
        return filtrelenmisListe;
    }

}
