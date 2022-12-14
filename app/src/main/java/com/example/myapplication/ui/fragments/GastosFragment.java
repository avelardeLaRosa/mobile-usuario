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

import java.util.ArrayList;
import java.util.List;

public class GastosFragment extends Fragment {

    private TableLayout table_gastos;
    private TextView txt_item_gastos;
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

        table_gastos = (TableLayout) view.findViewById(R.id.table_gastos);

        List<Producto> productos = new ArrayList<>();
        productos = dao.listar();


        for(Producto p: productos){
            View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.item_gastos_gastos,null,false);

            txt_item_gastos = tableRow.findViewById(R.id.txt_item_gastos);

            txt_item_gastos.setText("S/. - " + p.getEgresos());

            table_gastos.addView(tableRow);
        }

        return view;
    }
}