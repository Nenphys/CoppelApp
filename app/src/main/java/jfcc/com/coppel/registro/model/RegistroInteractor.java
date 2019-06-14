package jfcc.com.coppel.registro.model;

public interface RegistroInteractor {
    interface onCreateFinished{
        void onError(String error);
        void onSuccess();
    }
    void createUser(String uName,String correo, String pass, onCreateFinished listener);
}
