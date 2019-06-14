package jfcc.com.coppel.perfil;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jfcc.com.coppel.R;
import jfcc.com.coppel.login.LoginActivity;
import jfcc.com.coppel.net.Usuarios;
import jfcc.com.coppel.net.UsuariosApi;
import jfcc.com.coppel.net.netclass;
import jfcc.com.coppel.perfil.model.PerfilIclass;
import jfcc.com.coppel.perfil.presenter.PerfilPClass;
import jfcc.com.coppel.perfil.presenter.PerfilPresenter;
import jfcc.com.coppel.perfil.view.PerfilView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends Activity implements PerfilView, View.OnClickListener {

    private TextView nombre;
    private PerfilPresenter perfilPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nombre = findViewById(R.id.nombreusuario);
        findViewById(R.id.btnlogout).setOnClickListener(this);
        findViewById(R.id.btnborrar).setOnClickListener(this);
        perfilPresenter = new PerfilPClass(this, new PerfilIclass());
        getName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnborrar:
                break;
            case R.id.btnlogout:
                logOut();
                break;
        }
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void getName(){
        netclass net  = new netclass();
        UsuariosApi usuariosApi = net.getConection();
        Call<Usuarios> call = usuariosApi.getUsuario(getIntent().getStringExtra("correo"),getIntent().getStringExtra("pass"));
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Usuarios usuario = response.body();
                nombre.setText(usuario.getNombrecompleto());
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {

            }
        });
    }

    @Override
    public void showError(String error) {

    }
}
