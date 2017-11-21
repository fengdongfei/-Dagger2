package com.flot.darger2.dargerdemo.component;

import com.flot.darger2.dargerdemo.DaggerActivity;
import com.flot.darger2.dargerdemo.module.ActivityModule;
import com.flot.darger2.dargerokhttp.ActivityScope;
import com.flot.darger2.dargerokhttp.AppComponent;

import dagger.Component;

/**
 * Component需要用@Component注解来标识
 * 同时声明了modules为上面编写的ActivityComponent,然后提供了一个方法，叫做inject，用来在Activity中注入
 *
 最后，AndroidStudio -> Build -> Make Project

 写到这里的时候就可以Make Project了，完成之后apt会自动生成一个以Dagger开头的Component，比如，我们上面写的是ActivityComponent,
 生成了类名就为DaggerActivityComponent。这个类我们可以直接使用
 */
//Component之间也可以依赖的
@ActivityScope  //和Activity的生命周期绑定
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(DaggerActivity daggerActivity);
}