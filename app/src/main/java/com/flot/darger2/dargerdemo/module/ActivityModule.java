package com.flot.darger2.dargerdemo.module;

import com.flot.darger2.dargerdemo.DaggerActivity;
import com.flot.darger2.dargerdemo.DaggerPresenter;
import com.flot.darger2.dargerdemo.User;

import dagger.Module;
import dagger.Provides;

/**
 * Module的作用是用来提供生成依赖对象的
 * 比如我要注入DaggerPresenter，那么这个Module的作用就是需要生成一个DaggerPresenter的对象，来让Dagger2注入到DaggerActivity中
 编写Module有以下几个注意点：

 类需要用@Module来标明注解
 这里有一点规则，用@Provides注解的函数需要以provide开头，然后后面接什么内容都可以，看自己喜欢，事实上，经过我的测试，
 我把provideActivity()改成provideA()同样是可以注入成功的，所以大家可以知道，这里是根据返回值类型来标识的，方法名并不重要，
 只需要保证以provide开头即可
 * Created by fengdongfei on 2017/11/21.
 */

@Module
public class ActivityModule {
    private  User user;
    private DaggerActivity activity;

    public ActivityModule(DaggerActivity activity) {
        this.activity = activity;
    }
    public ActivityModule(DaggerActivity activity,User user) {
        this.activity = activity;
        this.user=user;
    }
    @Provides
    public DaggerActivity provideActivity() {
        return activity;
    }

    @Provides
    public User provideUser() {
        return user;
    }

    /**
     * 提供生成注入的对象生成的函数
     * @param activity
     * @param user
     * @return
     */
    @Provides
    public DaggerPresenter provideDaggerPresenter(DaggerActivity activity, User user) {
        return new DaggerPresenter(activity, user);
    }
}