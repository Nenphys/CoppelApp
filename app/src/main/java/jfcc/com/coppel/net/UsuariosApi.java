package jfcc.com.coppel.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuariosApi {
    @GET("usuario")
    Call<List<Usuarios>> getUsuarios();

    @GET("login/{correo},{pass}")
    Call<Status> getLogin(@Path("correo") String corrreo, @Path("pass") String pass);

    @GET("usuario/{correo},{pass}")
    Call<Usuarios> getUsuario(@Path("correo") String corrreo, @Path("pass") String pass);

    @POST("/usuario/createUser/{nombre},{correo},{password}")
    Call<Status> registrer(@Path("nombre") String nombre, @Path("correo") String corrreo, @Path("password") String password);

    @DELETE("/usuario/deleteUser/{nombre},{correo}")
    Call<Status> deleteUser(@Path("nombre") String nombre, @Path("correo") String corrreo);
}
