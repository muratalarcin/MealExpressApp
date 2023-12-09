package com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GirisYapViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo;
    private MutableLiveData<String> adSoyadLiveData;

    @Inject
    public GirisYapViewModel(YemeklerDaoRepository yrepo) {
        this.yrepo = yrepo;
        this.adSoyadLiveData = new MutableLiveData<>();
    }

    public LiveData<String> getAdSoyad() {
        return adSoyadLiveData;
    }

    // UI tarafından adSoyad güncellemesi için bu metodu kullanabilirsiniz
    public void updateAdSoyad(String adSoyad) {
        adSoyadLiveData.setValue(adSoyad);
        // ProfilViewModel içindeki adSoyad'ı güncelleyen metodu çağırın
        yrepo.setAdSoyad(adSoyad);
    }
}

