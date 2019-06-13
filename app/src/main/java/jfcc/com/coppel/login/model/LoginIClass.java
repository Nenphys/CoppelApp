package jfcc.com.coppel.login.model;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.List;

import jfcc.com.coppel.login.Interface.UsuariosApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.5:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UsuariosApi usuariosApi = retrofit.create(UsuariosApi.class);

                Call<LoginApi> call = usuariosApi.getLogin(uName, pass);
                call.enqueue(new Callback<LoginApi>() {
                    @Override
                    public void onResponse(Call<LoginApi> call, Response<LoginApi> response) {
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
                    public void onFailure(Call<LoginApi> call, Throwable t) {
                        listener.onError("Error: "+t);
                    }
                });
            }
        }, 1000);
    }

    //TODO: aqui hacemos todo lo el acceso hacia el servicio
}
