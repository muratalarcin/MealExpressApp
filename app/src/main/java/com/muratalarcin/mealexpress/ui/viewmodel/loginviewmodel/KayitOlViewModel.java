package com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KayitOlViewModel extends ViewModel {

    private YemeklerDaoRepository yrepo;
    private MutableLiveData<String> adSoyadLiveData;
    private SharedPreferences sharedPreferences; // SharedPreferences nesnesi

    @Inject
    public KayitOlViewModel(YemeklerDaoRepository yrepo, SharedPreferences sharedPreferences) {
        this.yrepo = yrepo;
        this.adSoyadLiveData = new MutableLiveData<>();
        this.sharedPreferences = sharedPreferences;
    }

    public LiveData<String> getAdSoyad() {
        return adSoyadLiveData;
    }

    public void updateAdSoyad(String adSoyad) {
        adSoyadLiveData.setValue(adSoyad);

        // SharedPreferences'e ad soyadı kaydet
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("adSoyad", adSoyad);
        editor.apply();

        // ProfilViewModel içindeki adSoyad'ı güncelleyen metodu çağırın
        yrepo.setAdSoyad(adSoyad);
    }
}

