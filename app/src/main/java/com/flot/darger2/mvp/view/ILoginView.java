package com.flot.darger2.mvp.view;

/**
 * Created by fengdongfei on 2017/11/21.
 */

public interface ILoginView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
    public void onSetProgressBarVisibility(int visibility);
}