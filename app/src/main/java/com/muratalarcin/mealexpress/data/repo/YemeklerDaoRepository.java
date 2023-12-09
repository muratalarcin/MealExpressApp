package com.muratalarcin.mealexpress.data.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muratalarcin.mealexpress.data.entity.CRUDCevap;
import com.muratalarcin.mealexpress.data.entity.SepetCevap;
import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.data.entity.YemeklerCevap;
import com.muratalarcin.mealexpress.retrofit.YemeklerDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public MutableLiveData<List<SepetString>> sepetListesi = new MutableLiveData<List<SepetString>>();
    private YemeklerDao ydao;
    private MutableLiveData<String> adSoyadLiveData = new MutableLiveData<>();

    // Dışarıdan bu değeri observe etmek için bir LiveData
    public LiveData<String> getAdSoyadLiveData() {
        return adSoyadLiveData;
    }

    // ViewModel içindeki adSoyad'ı güncelleyen bir metod
    public void setAdSoyad(String adSoyad) {
        adSoyadLiveData.setValue(adSoyad);
    }

    public YemeklerDaoRepository(YemeklerDao ydao) {
        this.ydao = ydao;
    }
    public void yemekleriYukle() {
        ydao.yemekleriYukle().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> liste = response.body().getYemekler();
                yemeklerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {

            }
        });
    }

    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        ydao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }

    public void sepetiYukle(String kullanici_adi) {
        ydao.sepetiYukle(kullanici_adi).enqueue(new Callback<SepetCevap>() {
            @Override
            public void onResponse(Call<SepetCevap> call, Response<SepetCevap> response) {
                List<SepetString> liste = response.body().getSepetListesi();
                sepetListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<SepetCevap> call, Throwable t) {

            }
        });
    }

    public void sepettenSil(String sepet_yemek_id, String kullanici_adi) {
        ydao.sepettenSil(sepet_yemek_id, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepetiYukle(kullanici_adi);
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }
}
