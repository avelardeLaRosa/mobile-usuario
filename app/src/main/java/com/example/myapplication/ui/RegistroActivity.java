package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Usuario;
import com.example.myapplication.network.ApiClient;
import com.example.myapplication.network.ApiUsuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity  {

    private ImageView backLogin;

    private TextInputEditText te_nombre;
    private TextInputEditText te_apellido;
    private TextInputEditText te_edad;
    private TextInputEditText te_usuario;
    private TextInputEditText te_password;
    private EditText te_img_url;

    private TextInputLayout ti_nombre;
    private TextInputLayout ti_apellido;
    private TextInputLayout ti_edad;
    private TextInputLayout ti_usuario;
    private TextInputLayout ti_password;
    private TextInputLayout ti_img_url;

    private Button btn_registrar;


    Usuario usuario = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);




        inicializar();

        ti_nombre.setHelperText("");
        ti_apellido.setHelperText("");
        ti_password.setHelperText("");
        ti_edad.setHelperText("");
        ti_usuario.setHelperText("");
        ti_password.setHelperText("");
        ti_img_url.setHelperText("");

        validarInputs();
        goBack();

        btn_registrar = (Button) findViewById(R.id.btn_registrar_usuario);

    }

    private void inicializar(){
        backLogin = (ImageView) findViewById(R.id.img_regreso);
        te_nombre = (TextInputEditText) findViewById(R.id.te_nombre);
        te_apellido = (TextInputEditText) findViewById(R.id.te_apellido);
        te_edad = (TextInputEditText) findViewById(R.id.te_edad);
        te_usuario = (TextInputEditText) findViewById(R.id.te_usuario);
        te_password = (TextInputEditText) findViewById(R.id.te_password);
        te_img_url = (EditText) findViewById(R.id.tx_img_url);

        ti_nombre = (TextInputLayout) findViewById(R.id.ti_nombre);
        ti_apellido = (TextInputLayout) findViewById(R.id.ti_apellido);
        ti_edad = (TextInputLayout) findViewById(R.id.ti_edad);
        ti_usuario = (TextInputLayout) findViewById(R.id.ti_usuario);
        ti_password = (TextInputLayout) findViewById(R.id.ti_password);
        ti_img_url = (TextInputLayout) findViewById(R.id.ti_img_url);

        btn_registrar = (Button) findViewById(R.id.btn_registrar_usuario);

    }

    private void goBack(){
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void validarInputs(){
        nombreFocusListener();
        apellidoFocusListener();
        edadFocusListener();
        usuarioFocusListener();
        passwordFocusListener();
        imgFocusListener();
    }



    private void nombreFocusListener(){
        te_nombre.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_nombre.setHelperText(validarNombre());
            }
        });
    }

    private String validarNombre(){
        String nombre = te_nombre.getText().toString().trim();

        if(nombre.isEmpty()){
            return "Debe ingresar su nombre";
        }
        return null;
    }

    private void apellidoFocusListener(){
        te_apellido.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_apellido.setHelperText(validarApellido());
            }
        });
    }

    private String validarApellido(){
        String apellido = te_apellido.getText().toString().trim();

        if(apellido.isEmpty()){
            return "Debe ingresar su apellido";
        }
        return null;


    }


    private void edadFocusListener(){
        te_edad.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_edad.setHelperText(validarEdad());
            }
        });
    }

    private String validarEdad(){
        String edad = te_edad.getText().toString().trim();
        if(edad.isEmpty()){
            return "Debe ingresar su edad";
        }
        return null;



    }

    private void usuarioFocusListener(){
        te_usuario.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_usuario.setHelperText(validarUsuario());
            }
        });
    }

    private String validarUsuario(){
        String usuario = te_usuario.getText().toString().trim();
        if(usuario.isEmpty()){
            return "Debe ingresar su edad";
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
                return "Corre con formato invalido";
            }
        }
        return null;



    }

    private void passwordFocusListener(){
        te_password.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_password.setHelperText(validarPassword());
            }
        });
    }

    private String validarPassword(){
        String password = te_password.getText().toString().trim();
        if(password.isEmpty()){
            return "Debe ingresar su contraseña";
        }
        return null;

    }

    private void imgFocusListener(){

        te_img_url.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                ti_img_url.setHelperText(validaImg());
            }
        });
    }

    private String validaImg(){



        String img = te_img_url.getText().toString().trim();
        if(img.isEmpty()){
            return "Debe ingresar su imagen";
        }
        return null;

    }


    public void registrar(View view) {

        String nombre = te_nombre.getText().toString().trim();
        String apellido = te_apellido.getText().toString().trim();
        String edad = te_edad.getText().toString().trim();
        String user = te_usuario.getText().toString().trim();
        String password = te_password.getText().toString().trim();
        String img_url = te_img_url.getText().toString().trim();



        if((nombre.length()==0) || (apellido.length()==0) || (edad.length()==0) || (user.length()==0) || (password.length()==0) || (img_url.length()==0)){
            Toast.makeText(RegistroActivity.this,"Campos vacios o invalidos",Toast.LENGTH_SHORT).show();
        }else{
            Usuario u = new Usuario(nombre,apellido,edad,user,password,img_url);
            Call<Usuario> call = ApiClient.getClient().create(ApiUsuario.class).add(u);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                    if(response.isSuccessful()){
                        usuario = response.body();
                        Toast.makeText(RegistroActivity.this,"Te registraste " + usuario.getNombre() + " " + usuario.getApellido(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(RegistroActivity.this,"No se pudo registrar",Toast.LENGTH_SHORT).show();
                }
            });
        }





    }

}