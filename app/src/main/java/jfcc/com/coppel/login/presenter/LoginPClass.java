package jfcc.com.coppel.login.presenter;

import jfcc.com.coppel.login.model.LoginInteractor;
import jfcc.com.coppel.login.view.LoginView;

public class LoginPClass implements LoginPresenter, LoginInteractor.onLoginFinished {

    private LoginView loginView;
    private LoginInteractor loginItr;

    public LoginPClass(LoginView loginView, LoginInteractor loginItr) {
        this.loginView = loginView;
        this.loginItr = loginItr;
    }


    @Override
    public void ValidateLogin(String uName, String pass) {
        if (loginView != null){
            loginView.showProgress();
        }
        loginItr.Login(uName, pass,this);
    }
    @Override
    public void onError(String  error) {
        if (loginView != null){
            loginView.showError(error);
            loginView.hideProgress();
        }
    }
    @Override
    public void onSuccess() {
        if(loginView != null){
            loginView.goHome();
            loginView.hideProgress();
        }
    }
}
