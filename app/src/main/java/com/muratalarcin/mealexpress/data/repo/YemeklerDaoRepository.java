package com.muratalarcin.mealexpress.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.data.entity.YemeklerCevap;
import com.muratalarcin.mealexpress.retrofit.YemeklerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    private YemeklerDao ydao;

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
}
