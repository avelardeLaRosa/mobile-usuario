package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.ui.fragments.DatosFragment;
import com.example.myapplication.ui.fragments.GastosFragment;
import com.example.myapplication.ui.fragments.IngresoGastosFragment;
import com.example.myapplication.ui.fragments.RegistroFragment;
import com.example.myapplication.ui.fragments.UsuarioFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager_tab);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        viewPagerAdapter.addFragment(new DatosFragment(), "Datos");
        viewPagerAdapter.addFragment(new IngresoGastosFragment(), "Ingreso/Gastos");
        viewPagerAdapter.addFragment(new GastosFragment(), "Gastos");
        viewPagerAdapter.addFragment(new RegistroFragment(), "Registro");
        viewPagerAdapter.addFragment(new UsuarioFragment(), "Usuario");

        viewPager.setAdapter(viewPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("password");
                editor.apply();
                startActivity(new Intent(MainActivity.this ,LoginActivity.class));
                finish();
            }
        });



    }


}