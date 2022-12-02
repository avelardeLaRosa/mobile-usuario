package com.example.myapplication.network;

import com.example.myapplication.model.Usuario;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiUsuario {

    @GET("usuarios/login")
    Call<Usuario> login(
            @QueryMap Map<String,String> params
            );

    @POST("usuarios")
    Call<Usuario> add(
            @Body Usuario usuario
    );

}
