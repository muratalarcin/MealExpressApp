package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    public YemeklerDaoRepository yrepo;
    public MutableLiveData<List<SepetString>> sepetListesi = new MutableLiveData<List<com.muratalarcin.mealexpress.data.entity.SepetString>>();

    @Inject
    public SepetViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        //sepetiYukle("murat");
        sepetListesi = yrepo.sepetListesi;
    }

    public void sepetiYukle(String kullanici_adi) {
        yrepo.sepetiYukle(kullanici_adi);


    }

    public void sepettenSil(String sepet_yemek_id, String kullanici_adi) {
        yrepo.sepettenSil(sepet_yemek_id, kullanici_adi);

    }

    public void yemekleriYukle() {
        yrepo.yemekleriYukle();
    }

}
