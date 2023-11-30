package com.muratalarcin.mealexpress.di;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;
import com.muratalarcin.mealexpress.retrofit.ApiUtils;
import com.muratalarcin.mealexpress.retrofit.YemeklerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
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

}
