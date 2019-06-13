package jfcc.com.coppel.login.Interface;

import java.util.List;

import jfcc.com.coppel.login.model.LoginApi;
import jfcc.com.coppel.login.model.Usuarios;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuariosApi {
    @GET("usuario")
    Call<List<Usuarios>> getUsuarios();

    @GET("login/{correo},{pass}")
    Call<LoginApi> getLogin(@Path("correo") String corrreo, @Path("pass") String pass);

}
