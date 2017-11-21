package com.flot.darger2.dargerokhttp;

import com.flot.darger2.dargerdemo.User;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

//@SingleTon注解，这里说明是全局单例的对象
@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {

    OkHttpClient getClient();

    Retrofit getRetrofit();

}