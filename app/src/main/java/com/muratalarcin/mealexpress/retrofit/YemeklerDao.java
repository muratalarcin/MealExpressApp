package com.muratalarcin.mealexpress.retrofit;

import com.muratalarcin.mealexpress.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YemeklerDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/ -> base url
    //yemekler/tumYemekleriGetir.php -> api url

    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekleriYukle();
}
