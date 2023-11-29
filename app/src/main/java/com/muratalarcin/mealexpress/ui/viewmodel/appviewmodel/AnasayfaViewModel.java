package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

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

}
