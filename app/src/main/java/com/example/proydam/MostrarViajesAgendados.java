package com.example.proydam;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


// Esta clase muestra los viajes agendados en una lista
public class MostrarViajesAgendados extends AppCompatActivity
{
    // Estos son los arreglos que almacenan los datos de los viajes
    int [] tam_id;
    String [] nombreApellido, noTelefono, fechastring, horastring, dondeRecogerlo, descripcionViaje;
    // Este es el objeto que maneja la base de datos
    DeveloperBD2 dbHelper;
    // Este es el objeto que muestra la lista de viajes
    ListView listView;

    // Este es el objeto que muestra el icono de basura para eliminar un viaje
    ImageView basura;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_viajes_agendados);
        listView = findViewById(R.id.listViewMostrar);
        verAgenda();

    }

    // Este método consulta la base de datos y muestra los viajes en la lista
    private void verAgenda(){
        dbHelper = new DeveloperBD2(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select *from AGENDA_VIAJES",null);
        if(cursor.getCount()>0){
            // Se inicializan los arreglos con el tamaño del cursor
            tam_id = new int[cursor.getCount()];
            nombreApellido = new String[cursor.getCount()];
            noTelefono = new String[cursor.getCount()];
            fechastring = new String[cursor.getCount()];
            horastring = new String[cursor.getCount()];
            dondeRecogerlo = new String[cursor.getCount()];
            descripcionViaje = new String[cursor.getCount()];

            int contador = 0;

            // Se recorre el cursor y se asignan los valores a los arreglos
            while (cursor.moveToNext()){
                tam_id[contador] = cursor.getInt(0);
                nombreApellido[contador] = cursor.getString(1);
                noTelefono[contador] = cursor.getString(2);
                fechastring[contador] = cursor.getString(3);
                horastring[contador] = cursor.getString(4);
                dondeRecogerlo[contador] = cursor.getString(5);
                descripcionViaje[contador] = cursor.getString(6);

                contador++;

                // Se crea un adaptador personalizado para mostrar los viajes en la lista
                AdapterViajes myAdapter = new AdapterViajes();
                listView.setAdapter(myAdapter);
            }
        }

    }

    // Esta clase interna define el adaptador personalizado para la lista de viajes
    private class AdapterViajes extends BaseAdapter {
        @Override
        public int getCount() {
            return nombreApellido.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Estos son los objetos que muestran los datos de cada viaje en la lista
            TextView nombre, telefono, fecha, hora, recoger, descripcion;
            ImageView basura;
            CardView cardView;
            convertView = LayoutInflater.from(MostrarViajesAgendados.this).inflate(R.layout.cardview_viajes,parent,false);
            nombre = convertView.findViewById(R.id.nombreMostrar);
            telefono = convertView.findViewById(R.id.telefonoMostrar);
            fecha = convertView.findViewById(R.id.fechaMostrar);
            hora = convertView.findViewById(R.id.horaMostrar);
            recoger = convertView.findViewById(R.id.recogerMostrar);
            descripcion = convertView.findViewById(R.id.descripcionMostrar);
            basura=convertView.findViewById(R.id.basura);
            cardView = convertView.findViewById(R.id.cardview);

            // Se asignan los valores de los arreglos a los objetos de la vista
            nombre.setText(nombreApellido[position]);
            telefono.setText(noTelefono[position]);
            fecha.setText(fechastring[position]);
            hora.setText(horastring[position]);
            recoger.setText(dondeRecogerlo[position]);
            descripcion.setText(descripcionViaje[position]);

            // Se define el evento click del icono de basura para eliminar un viaje
            basura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                    long respuesta = sqLiteDatabase.delete("AGENDA_VIAJES","id=" +tam_id[position],null);
                    if(respuesta != -1){
                        Toast.makeText(MostrarViajesAgendados.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                        verAgenda();
                        finish(); // Termina el activity actual
                        startActivity(getIntent()); // Inicia el mismo activity con el mismo intent
                    }
                }
            });
            return convertView;
        }
    }
}
