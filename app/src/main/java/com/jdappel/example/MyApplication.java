package com.jdappel.example;

import android.content.SharedPreferences;

import com.jdappel.example.dagger.DaggerTestComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

import static com.jdappel.example.BuildConfig.APPLICATION_ID;

/**
 * Created by Archinamon on 10/5/15.
 */
public class MyApplication extends DaggerApplication {

    public static final String LANG_KEY = "k_lang";

    private SharedPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        mPreferences = getSharedPreferences(APPLICATION_ID, MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    @Override
    protected AndroidInjector<MyApplication> applicationInjector() {
        return DaggerTestComponent.builder().create(this);
    }
}
