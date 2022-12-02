package com.example.myapplication.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ProductoDAO;
import com.example.myapplication.model.Producto;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class DatosFragment extends Fragment {

    private TableLayout tl_productos;
    private TextView tv_fecha;
    private TextView tv_monto;
    private TextView tv_descripcion;
    ProductoDAO dao=null;
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        dao = new ProductoDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos, container, false);

        tl_productos = (TableLayout) view.findViewById(R.id.table_layout);

        List<Producto> productos = new ArrayList<>();
        productos = dao.listar();


        for(Producto p: productos){
            View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.item_table,null,false);

            tv_fecha = tableRow.findViewById(R.id.tv_fecha);
            tv_descripcion = tableRow.findViewById(R.id.tv_descripcion);
            tv_monto = tableRow.findViewById(R.id.tv_monto);

            tv_fecha.setText(p.getFecha());
            tv_descripcion.setText(p.getDescripcion());
            tv_monto.setText("S/. " + p.getMonto());

            tl_productos.addView(tableRow);
        }




        return view;
    }





}

