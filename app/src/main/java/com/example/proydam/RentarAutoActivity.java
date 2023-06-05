package com.example.proydam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RentarAutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentar_auto);
    }

    // Metodo para ir a CamionetasVansActivity al hacer click en el boton CAMIONETAS Y VANS
    public void CamionetasVansActivity(View view){
        Intent intent = new Intent(this, CamionetasVansActivity.class);
        startActivity(intent);
    }

    // Metodo para ir a CarrosMedianosActivity al hacer click en el boton CARROS MEDIANOS
    public void CarrosMedianosActivity(View view){
        Intent intent = new Intent(this, CarrosMedianosActivity.class);
        startActivity(intent);
    }
}