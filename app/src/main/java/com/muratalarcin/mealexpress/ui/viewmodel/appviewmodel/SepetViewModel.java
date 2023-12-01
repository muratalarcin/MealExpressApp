package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepetViewModel extends ViewModel {
    public YemeklerDaoRepository yrepo;
    public MutableLiveData<List<Sepet>> sepetListesi = new MutableLiveData<>();

    @Inject
    public SepetViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        sepetListesi = yrepo.sepetListesi;
    }



}
