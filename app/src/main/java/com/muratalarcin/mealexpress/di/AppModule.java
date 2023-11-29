package com.muratalarcin.mealexpress.di;

import android.app.Application;

import com.muratalarcin.mealexpress.data.repo.YemeklerDaoRepository;

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
    public YemeklerDaoRepository provideYemeklerDaoRepository() {
        return new YemeklerDaoRepository();
    }

}
