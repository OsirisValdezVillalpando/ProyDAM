package com.example.proydam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variable donde guardaremos el link de la ubicacion
    private String urlUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignamos el link de la ubicacion a la variable String
        urlUbicacion = "https://www.google.com/maps/@21.4752954,-103.0888359,3a,75y,96.79h,89.33t/data=!3m6!1e1!3m4!1sETFEWhAdkesMdUoweE9znQ!2e0!7i16384!8i8192";
    }

    // Metodo que al hacer click en el boton UBICACION habre el link para visualizar la ubicacion del negocio
    public void Ubicacion(View view) {
        Uri uri = Uri.parse(urlUbicacion);
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    // Metodo para ir a RentarAutoActivity al hacer click en el boton RENTAR AUTO
    public void RentarAutoActivity(View view){
        Intent intent = new Intent(this, RentarAutoActivity.class);
        startActivity(intent);
    }

    // Metodo para ir a AgendarViajeActivity al hacer click en el boton AGENDAR VIAJE
    public void AgendarViajeActivity(View view){
        Intent intent = new Intent(this, AgendarViajeActivity.class);
        startActivity(intent);
    }

    // Metodo para llamar a la negocio al hacer click en el boton LLAMAR
    public void Llamar(View view) {
        Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4671058481"));
        startActivity(it);
    }
}