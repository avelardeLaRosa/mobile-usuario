package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Usuario;
import com.example.myapplication.network.ApiClient;
import com.example.myapplication.network.ApiUsuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Usuario usuario =  new Usuario();

    private TextInputEditText te_usuario;
    private TextInputEditText te_password;
    private TextInputLayout ti_usuario;
    private TextInputLayout ti_password;
    private Button btn_ingresar;
    private TextView go_registrar;

    int intentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();


        ti_usuario.setHelperText("");
        ti_password.setHelperText("");





        validarInputs();

        session();
        guardarSesion();

        goRegistrar();
    }

    private void inicializar(){
        te_usuario = findViewById(R.id.te_usuario);
        te_password = findViewById(R.id.te_password);
        ti_usuario = findViewById(R.id.ti_usuario);
        ti_password = findViewById(R.id.ti_password);
        btn_ingresar = findViewById(R.id.btn_ingresar_login);
        go_registrar = findViewById(R.id.txt_registrate);
    }

    private void goRegistrar(){
        go_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });
    }


    private void session() {


        btn_ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if((te_usuario.getText().toString().length()==0) || (te_password.getText().toString().length()==0)){
                    Toast.makeText(LoginActivity.this, "Usuario y contraseña vacios!", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> params = new HashMap<>();
                    params.put("usuario", te_usuario.getText().toString().trim() );
                    params.put("password", te_password.getText().toString().trim() );

                    Call<Usuario> call = ApiClient.getClient().create(ApiUsuario.class).login(params);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.isSuccessful()){
                                usuario = response.body();
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("nombre", usuario.getNombre());
                                editor.putString("apellido", usuario.getApellido());
                                editor.putString("img", usuario.getImg_url());
                                editor.putString("password", usuario.getPassword());
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "Bienvenido " + usuario.getNombre()+" "+usuario.getApellido(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                            intentos++;
                            if(intentos==3){
                                Toast.makeText(LoginActivity.this, "Se alcanzo el numero de intentos permitidos!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Sesion Invalida", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });
    }

    public void guardarSesion(){
        boolean estado=true;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref = preferences.getString("password","");
        if(!pref.equals("")){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        guardarSesion();
    }



    private void validarInputs(){

        te_usuario.setOnFocusChangeListener((v, focus) -> {
               if(!focus){
                ti_usuario.setHelperText(validarEmail());
                }
        });
        te_password.setOnFocusChangeListener((v, focus) -> {
            if(!focus){
                ti_password.setHelperText(validarPassword());
            }
        });
    }

    private String validarEmail() {

        String emailText = te_usuario.getText().toString().trim();
        if(emailText.isEmpty()){
            return "Debe ingresar email";
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                return "Correo con formato invalido";
            }
        }
        return null;
    }

    private String validarPassword() {
        String passwordText = te_password.getText().toString().trim();
        if(passwordText.isEmpty()){
            return "Debe ingresar contraseña";
        }
        return null;
    }

}