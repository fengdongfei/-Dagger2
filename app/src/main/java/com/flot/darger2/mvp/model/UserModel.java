package com.flot.darger2.mvp.model;

/**
 * Created by fengdongfei on 2017/11/21.
 */

public class UserModel implements IUser {
    public UserModel(String mvp, String mvp1) {

    }


    @Override
    public int checkUserValidity(String name, String passwd) {
        if (passwd.equals("123456"))
            return 0;
        else
            return -1;
    }
}
