package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetayViewModel extends ViewModel {
    public YemeklerDaoRepository yrepo;

    @Inject
    public DetayViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
    }



}
