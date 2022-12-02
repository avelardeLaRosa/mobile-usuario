package com.example.myapplication.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dao.ProductoDAO;
import com.example.myapplication.model.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class GastosFragment extends Fragment {

    private TableLayout tb_gastos;
    private TextView tv_descripcion;
    private TextView tv_ingresos;
    private TextView tv_gastos;

    ProductoDAO dao = null;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        dao = new ProductoDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gastos, container, false);

        tb_gastos = (TableLayout) view.findViewById(R.id.table_layout_gastos);

        List<Producto> productos = new ArrayList<>();
        productos = dao.listar();


        for(Producto p: productos){
            View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.item_table_gastos,null,false);

            tv_descripcion = tableRow.findViewById(R.id.tv_descripcion_gastos);
            tv_gastos = tableRow.findViewById(R.id.tv_gastos_gastos);
            tv_ingresos = tableRow.findViewById(R.id.tv_ingresos_gastos);

            tv_descripcion.setText(p.getDescripcion());
            tv_ingresos.setText("S/. " + p.getIngresos());
            tv_gastos.setText("S/. - " + p.getEgresos());

            tb_gastos.addView(tableRow);
        }


        return view;
    }
}