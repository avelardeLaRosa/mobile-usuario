package com.example.myapplication.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsuarioFragment extends Fragment {

    private TextView nom_user;
    private CircleImageView circle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        nom_user = view.findViewById(R.id.tx_nombre_usuario);
        circle = view.findViewById(R.id.img_usuario);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String nombre = preferences.getString("nombre","");
        String apellido = preferences.getString("apellido","");
        String img = preferences.getString("img","");

        nom_user.setText(nombre + " " + apellido);
        Glide.with(view).load(img).into(circle);

        return view;
    }



}