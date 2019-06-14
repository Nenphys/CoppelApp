package jfcc.com.coppel.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class netclass  {

    public UsuariosApi getConection (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(UsuariosApi.class);

    }
}
