package com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfilViewModel extends ViewModel {
    private MutableLiveData<String> adSoyadLiveData;
    public YemeklerDaoRepository yrepo;
    private SharedPreferences sharedPreferences;

    @Inject
    public ProfilViewModel(YemeklerDaoRepository yrepo, SharedPreferences sharedPreferences) {
        this.yrepo = yrepo;
        this.adSoyadLiveData = new MutableLiveData<>();
        this.sharedPreferences = sharedPreferences;

        // Uygulama başladığında SharedPreferences'ten ad soyadı yükleyin
        String savedAdSoyad = sharedPreferences.getString("adSoyad", "");
        adSoyadLiveData.setValue(savedAdSoyad);

        // ProfilViewModel içindeki adSoyad'ı güncelleyen metodu çağırın
        yrepo.setAdSoyad(savedAdSoyad);
    }

    public LiveData<String> getAdSoyad() {
        return adSoyadLiveData;
    }

    public void updateAdSoyad(String adSoyad) {
        adSoyadLiveData.setValue(adSoyad);
    }
}


