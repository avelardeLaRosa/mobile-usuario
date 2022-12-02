package com.example.myapplication.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ProductoDAO;
import com.example.myapplication.model.Producto;
import com.google.android.material.snackbar.Snackbar;


public class RegistroFragment extends Fragment {

    private Switch aSwitch;
    private EditText et_ingreso;
    private EditText et_gastos;
    private Button btn_registrar;
    private EditText et_descripcion;
    private EditText et_fecha;
    private EditText et_monto;
    ProductoDAO dao=null;


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
         dao = new ProductoDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        et_ingreso =  (EditText) view.findViewById(R.id.et_ingresos);
        et_gastos = (EditText) view.findViewById(R.id.et_gastos);
        et_descripcion = (EditText) view.findViewById(R.id.et_descripcion);
        et_fecha = (EditText) view.findViewById(R.id.et_fecha);
        et_monto = (EditText) view.findViewById(R.id.et_monto);
        btn_registrar = (Button) view.findViewById(R.id.btn_registrar_ingresos);
        aSwitch = (Switch) view.findViewById(R.id.id_switch);


        et_descripcion.setEnabled(false);
        et_fecha.setEnabled(false);
        et_monto.setEnabled(false);
        et_gastos.setEnabled(false);
        et_ingreso.setEnabled(false);
        aSwitch.setChecked(false);
        activar();


        btn_registrar.setOnClickListener(this::insertar);

        return view;
    }

    public void activar() {

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(v.getId() == R.id.id_switch){
                        if(aSwitch.isChecked()){
                            et_descripcion.setEnabled(true);
                            et_fecha.setEnabled(true);
                            et_monto.setEnabled(true);
                            et_gastos.setEnabled(true);
                            et_ingreso.setEnabled(true);
                            Toast.makeText(getContext(), "Ingresos activados", Toast.LENGTH_SHORT).show();

                        }else{
                            et_descripcion.setEnabled(false);
                            et_fecha.setEnabled(false);
                            et_monto.setEnabled(false);
                            et_gastos.setEnabled(false);
                            et_ingreso.setEnabled(false);
                            Toast.makeText(getContext(),"Ingresos Desactivados", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });

    }

    private void insertar(View view) {

        String descripcion = et_descripcion.getText().toString().trim();
        String fecha = et_fecha.getText().toString().trim();
        String monto = et_monto.getText().toString().trim();
        String ingresos = et_ingreso.getText().toString().trim();
        String egresos = et_gastos.getText().toString().trim();

        Producto producto = new Producto(descripcion,fecha,monto,ingresos,egresos);

        long respuesta = dao.ingresar(producto);

        if(respuesta > 0){
            Snackbar.make(view,"¡Producto Guardado!",Snackbar.LENGTH_SHORT).show();
            et_ingreso.setText("");
            et_gastos.setText("");
            et_descripcion.setText("");
            et_fecha.setText("");
            et_monto.setText("");
            aSwitch.setChecked(false);
            et_descripcion.setEnabled(false);
            et_fecha.setEnabled(false);
            et_monto.setEnabled(false);
            et_gastos.setEnabled(false);
            et_ingreso.setEnabled(false);

        }else{
            Snackbar.make(view,"¡No se pudo registrar producto!",Snackbar.LENGTH_SHORT).show();

        }
    }
}