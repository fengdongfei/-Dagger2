package com.flot.darger2.mvp.present;

public interface ILoginPresenter {
    void clear();
	void doLogin(String name, String passwd);
	void setProgressBarVisiblity(int visiblity);
}