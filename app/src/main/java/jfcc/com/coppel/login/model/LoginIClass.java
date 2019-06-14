package jfcc.com.coppel.login.model;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import jfcc.com.coppel.net.Status;
import jfcc.com.coppel.net.UsuariosApi;
import jfcc.com.coppel.net.netclass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginIClass implements LoginInteractor {
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void Login(final String uName, final String pass, final onLoginFinished listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(uName) || TextUtils.isEmpty(pass)){
                    listener.onError("Campos vacios");
                    return;
                }
                netclass net  = new netclass();
                UsuariosApi usuariosApi = net.getConection();
                Call<Status> call = usuariosApi.getLogin(uName, pass);
                call.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                       if(!response.isSuccessful()){
                           return;
                       }
                       if (response.body().getStatus().equals("Success")){
                           listener.onSuccess();
                       } else {
                           listener.onError("corrreo o contrasena incorrecta");
                       }
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        listener.onError("Error: "+t);
                    }
                });
            }
        }, 1000);
    }

    //TODO: aqui hacemos todo lo el acceso hacia el servicio
}
