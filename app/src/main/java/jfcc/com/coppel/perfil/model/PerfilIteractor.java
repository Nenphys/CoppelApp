package jfcc.com.coppel.perfil.model;

public interface PerfilIteractor {
    interface onDeleteFinished {
        void onError(String error);
        void onSuccess();
    }
    void onDeleteAccount(String name,String correo,onDeleteFinished listener);
}
