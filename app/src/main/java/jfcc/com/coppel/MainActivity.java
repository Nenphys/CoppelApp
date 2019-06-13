package jfcc.com.coppel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import jfcc.com.coppel.login.Interface.UsuariosApi;
import jfcc.com.coppel.login.model.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView myJsonTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myJsonTv = (findViewById(R.id.jsonText));
        getUser();
    }

    private void getUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuariosApi usuariosApi = retrofit.create(UsuariosApi.class);

        Call<List<Usuarios>> call = usuariosApi.getUsuarios();

        call.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if (!response.isSuccessful()){
                    return;
                }
                List<Usuarios> usuario = response.body();
                for(Usuarios u: usuario){
                    String usuarios = "";
                    usuarios += u.getNombrecompleto()+"\n";
                    usuarios += u.getId()+"\n";
                    usuarios += u.getCorreo()+"\n";
                    usuarios += u.getPassword()+"\n";
                    myJsonTv.append(usuarios);
                }

            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });
    }
}
