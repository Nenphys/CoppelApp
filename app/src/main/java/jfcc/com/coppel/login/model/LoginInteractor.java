package jfcc.com.coppel.login.model;

public interface LoginInteractor {
    interface onLoginFinished{
        void onError(String error);
        void onSuccess();
    }
    void Login(String uName, String pass, onLoginFinished listener);
}
