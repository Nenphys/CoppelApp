package jfcc.com.coppel.registro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import jfcc.com.coppel.R;
import jfcc.com.coppel.login.LoginActivity;
import jfcc.com.coppel.registro.View.RegistroView;
import jfcc.com.coppel.registro.model.RegistroIClass;
import jfcc.com.coppel.registro.presenter.RegistroPClass;
import jfcc.com.coppel.registro.presenter.RegistroPresenter;

public class RegistroActivity extends Activity implements RegistroView, View.OnClickListener {
    private ProgressBar pBar;
    private EditText uName;
    private EditText correo;
    private EditText pass;
    private RegistroPresenter rPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        pBar = findViewById(R.id.progressBarc);
        uName = findViewById(R.id.edtnombrecompleto);
        correo = findViewById(R.id.edtcorreo);
        pass = findViewById(R.id.edtpassword);
        findViewById(R.id.btncrear).setOnClickListener(this);
        rPresenter = new RegistroPClass(this, new RegistroIClass());
    }

    @Override
    public void onClick(View view) {
        rPresenter.ValidateCreateNewUser(uName.getText().toString(),correo.getText().toString(),pass.getText().toString());
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
    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
