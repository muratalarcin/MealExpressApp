package com.muratalarcin.mealexpress.di;

import android.app.Application;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule extends Application {
}
