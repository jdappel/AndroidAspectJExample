package com.jdappel.example.dagger;

import com.jdappel.example.MainActivity;
import com.jdappel.example.MyApplication;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules={AndroidAppModule.class, MyAppScopeModule.class, AndroidInjectionModule.class})
public interface TestComponent extends AndroidInjector<MyApplication> {

   void inject(MainActivity activity);

   @dagger.Component.Builder
   abstract class Builder extends AndroidInjector.Builder<MyApplication> {}
}