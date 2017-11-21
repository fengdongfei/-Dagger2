package com.flot.darger2.dargerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.flot.darger2.dargerdemo.component.DaggerActivityComponent;
import com.flot.darger2.dargerdemo.module.ActivityModule;
import com.flot.darger2.dargerokhttp.ApiModule;
import com.flot.darger2.dargerokhttp.AppComponent;
import com.flot.darger2.dargerokhttp.BApplication;
import com.flot.darger2.dargerokhttp.DaggerAppComponent;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 注入一个Presenter，然后通过Presenter来设置TextView显示内容为user.name
 * Created by fengdongfei on 2017/11/21.
 */

public class DaggerActivity extends AppCompatActivity {
    private static final String TAG = "ACT";
    @Inject
    DaggerPresenter presenter;
    @Inject
    OkHttpClient client;

    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this,new User("kiti")))
                //dagger expects you to add the appcomponent that your ActivityComponent dependencies on
                .appComponent(((BApplication) getApplication()).getAppComponent())
                .build()
                .inject(this);
        presenter.showUserName();
        Log.i(TAG, "client = " + (client == null ? "null" : client));
        Log.i(TAG, "retrofit = " + (retrofit == null ? "null" : retrofit));
    }

    public void showUserName(String name) {
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }

    //由于ActivityComponent 之间的依赖，使用DaggerActivityComponent
    private void inject() {
        AppComponent appComponent = ((BApplication) getApplication()).getAppComponent();
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this,new User("kiti")))
                .build().inject(this);
    }
}
