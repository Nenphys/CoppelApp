package jfcc.com.coppel.perfil.presenter;

import android.widget.TextView;

import jfcc.com.coppel.perfil.model.PerfilIteractor;
import jfcc.com.coppel.perfil.view.PerfilView;

public class PerfilPClass implements PerfilPresenter, PerfilIteractor.onDeleteFinished {
    private PerfilView perfilView;
    private PerfilIteractor perfilItr;

    public PerfilPClass(PerfilView perfilView, PerfilIteractor perfilItr) {
        this.perfilView = perfilView;
        this.perfilItr = perfilItr;
    }

    @Override
    public void onError(String error) {
        if(perfilView != null){
            perfilView.showError(error);
        }

    }

    @Override
    public void onSuccess() {
        if(perfilView != null){
            perfilView.logOut();
        }
    }


    @Override
    public void deleteAccount(String name, String correo) {
        if (perfilView != null) {
            perfilItr.onDeleteAccount(name, correo, this);
        }
    }
}
