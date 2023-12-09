package com.muratalarcin.mealexpress.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;
import com.muratalarcin.mealexpress.retrofit.ApiUtils;
import com.muratalarcin.mealexpress.retrofit.YemeklerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository provideYemeklerDaoRepository(YemeklerDao ydao) {
        return new YemeklerDaoRepository(ydao);
    }

    @Provides
    @Singleton
    public YemeklerDao provideYemeklerDao() {
        return ApiUtils.getYemeklerDao();
    }

    @Provides
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("your_pref_name", Context.MODE_PRIVATE);
    }
}
