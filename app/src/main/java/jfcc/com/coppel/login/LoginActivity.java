package jfcc.com.coppel.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import jfcc.com.coppel.MainActivity;
import jfcc.com.coppel.R;
import jfcc.com.coppel.login.model.LoginIClass;
import jfcc.com.coppel.login.presenter.LoginPClass;
import jfcc.com.coppel.login.presenter.LoginPresenter;
import jfcc.com.coppel.login.view.LoginView;
import jfcc.com.coppel.perfil.PerfilActivity;
import jfcc.com.coppel.registro.RegistroActivity;

public class LoginActivity extends Activity implements LoginView,View.OnClickListener {



    private ProgressBar pBar;
    private EditText uName;
    private EditText pass;
    private LoginPresenter lPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pBar = findViewById(R.id.progressBar);
        uName = findViewById(R.id.uName);
        pass = findViewById(R.id.edpass);
        findViewById(R.id.btnvalidate).setOnClickListener(this);
        findViewById(R.id.btnregistro).setOnClickListener(this);
        lPresenter = new LoginPClass(this, new LoginIClass());
    }
    @Override
    public void showProgress() {
        pBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        uName.setError(error);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, PerfilActivity.class);
        intent.putExtra("correo", uName.getText().toString());
        intent.putExtra("pass", pass.getText().toString());
        startActivity(intent);
        onDestroy();
    }

    @Override
    public void goRegistrer() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnvalidate:
                    lPresenter.ValidateLogin(uName.getText().toString(),pass.getText().toString());
                break;
            case R.id.btnregistro:
                    goRegistrer();
                break;
        }


    }
}
