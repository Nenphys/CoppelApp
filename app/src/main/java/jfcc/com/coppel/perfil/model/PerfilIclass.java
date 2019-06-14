package jfcc.com.coppel.perfil.model;

import android.text.TextUtils;

import java.util.List;

import jfcc.com.coppel.net.Status;
import jfcc.com.coppel.net.Usuarios;
import jfcc.com.coppel.net.UsuariosApi;
import jfcc.com.coppel.net.netclass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilIclass implements PerfilIteractor {
    @Override
    public void onDeleteAccount(String name, String correo, final onDeleteFinished listener) {
        netclass net  = new netclass();
        UsuariosApi usuariosApi = net.getConection();
        Call<Status> call = usuariosApi.deleteUser(name,correo);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(!response.isSuccessful()){
                    return;
                }
                if (response.body().getStatus().equals("Success")){
                    listener.onSuccess();
                } else {
                    listener.onError("No se pudo borrar cuenta");
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                listener.onError(""+t);
            }
        });
    }

}
