package jfcc.com.coppel.registro.model;

import android.text.TextUtils;

import jfcc.com.coppel.net.Status;
import jfcc.com.coppel.net.UsuariosApi;
import jfcc.com.coppel.net.netclass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroIClass implements RegistroInteractor {


    @Override
    public void createUser(String name,String correo, String pass, final onCreateFinished listener) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(correo)){
            listener.onError("Campos vacios");
            return;
        }
        netclass net  = new netclass();
        UsuariosApi usuariosApi = net.getConection();
        Call<Status> call = usuariosApi.registrer(name,correo,pass);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(!response.isSuccessful()){
                    return;
                }
                if (response.body().getStatus().equals("Success")){
                    listener.onSuccess();
                } else {
                    listener.onError("Campos incorrectos");
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                listener.onError("Error: "+t);
            }
        });
    }
}
