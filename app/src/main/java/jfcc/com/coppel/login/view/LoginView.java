package jfcc.com.coppel.login.view;

public interface LoginView {

    void showProgress();
    void hideProgress();
    void showError(String error);
    void goHome();
    void goRegistrer();
}
