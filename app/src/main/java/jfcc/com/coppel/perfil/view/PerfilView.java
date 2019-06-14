package jfcc.com.coppel.perfil.view;

import android.widget.TextView;

import org.w3c.dom.Text;

public interface PerfilView {

    void deleteAccount();
    void logOut();
    void showError(String error);

}
