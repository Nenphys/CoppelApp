package jfcc.com.coppel.registro.presenter;

import jfcc.com.coppel.registro.View.RegistroView;
import jfcc.com.coppel.registro.model.RegistroInteractor;

public class RegistroPClass implements RegistroPresenter, RegistroInteractor.onCreateFinished {
    private RegistroView registroView;
    private RegistroInteractor registroItr;

    public RegistroPClass(RegistroView registroView, RegistroInteractor registroItr) {
        this.registroView = registroView;
        this.registroItr = registroItr;
    }

    @Override
    public void onError(String error) {
        if (registroView != null){
            registroView.showError(error);
            registroView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(registroView != null){
            registroView.goLogin();
            registroView.hideProgress();
        }
    }

    @Override
    public void ValidateCreateNewUser(String uName, String email, String pass) {
        if (registroView != null){
            registroView.showProgress();
        }
        registroItr.createUser(uName,email, pass,this);
    }
}
