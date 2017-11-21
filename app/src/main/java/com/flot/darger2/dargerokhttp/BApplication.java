package com.flot.darger2.dargerokhttp;

import android.app.Application;

/**
 * Created by fengdongfei on 2017/11/21.
 */

public class BApplication extends Application {
    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
