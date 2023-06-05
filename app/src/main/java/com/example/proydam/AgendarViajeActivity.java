package com.example.proydam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

// Esta clase es una activity que permite al usuario agendar un viaje y enviar los datos por SMS
public class AgendarViajeActivity extends AppCompatActivity {

    // Declarar las variables para los elementos de la interfaz de usuario
    TextView nombreApellido;
    TextView noTelefono;
    TextView fecha;
    TextView hora;
    TextView dondeRecogerlo;
    TextView descripcionViaje;
    Button enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el layout de la actividad
        setContentView(R.layout.activity_agendar_viaje);

        // Obtener las referencias de los elementos de la interfaz de usuario usando el método findViewById()
        nombreApellido = findViewById(R.id.txtpNombreApellido);
        noTelefono = findViewById(R.id.txtpNoTelefono);
        fecha = findViewById(R.id.txtpFecha);
        hora = findViewById(R.id.txtpHora);
        dondeRecogerlo = findViewById(R.id.txtpDondeRecogerlo);
        descripcionViaje = findViewById(R.id.txtpDescripcionViaje);
        enviar = findViewById(R.id.btnEnviarAgendarViaje);

    }
    // Este método se ejecuta cuando el usuario pulsa el botón enviar
    public void EnviarAgendarViaje (View view){
        // Comprobar si el campo nombreApellido está vacío
        if (nombreApellido.getText().toString().isEmpty()){
            // Mostrar un mensaje de error en el campo
            nombreApellido.setError("Agregue su nombre y apellido");
            // Mostrar un mensaje emergente en la pantalla
            Toast.makeText(getApplicationContext(),"Agregue su nombre y apellido",Toast.LENGTH_SHORT).show();
        }else{
            // Comprobar si el campo noTelefono está vacío
            if(noTelefono.getText().toString().isEmpty()){
                // Mostrar un mensaje de error en el campo
                noTelefono.setError("Agregue su número de teléfono");
                // Mostrar un mensaje emergente en la pantalla
                Toast.makeText(getApplicationContext(),"Agregue su número de teléfono",Toast.LENGTH_SHORT).show();
            }
            else{
                // Comprobar si el campo fecha está vacío
                if(fecha.getText().toString().isEmpty()){
                    // Mostrar un mensaje de error en el campo
                    fecha.setError("Agregue la fecha del viaje");
                    // Mostrar un mensaje emergente en la pantalla
                    Toast.makeText(getApplicationContext(),"Agregue la fecha del viaje",Toast.LENGTH_SHORT).show();
                }
                else{
                    // Comprobar si el campo hora está vacío
                    if(hora.getText().toString().isEmpty()){
                        // Quitar el error del campo fecha
                        fecha.setError(null);
                        // Quitar el foco del campo fecha
                        fecha.clearFocus();
                        // Mostrar un mensaje de error en el campo hora
                        hora.setError("Agregue la hora del viaje");
                        // Mostrar un mensaje emergente en la pantalla
                        Toast.makeText(getApplicationContext(),"Agregue la hora del viaje",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        // Comprobar si el campo dondeRecogerlo está vacío
                        if (dondeRecogerlo.getText().toString().isEmpty()){
                            // Quitar el error del campo hora
                            hora.setError(null);
                            // Quitar el foco del campo hora
                            hora.clearFocus();
                            // Mostrar un mensaje de error en el campo dondeRecogerlo
                            dondeRecogerlo.setError("Agregue dónde recojerlo");
                            // Mostrar un mensaje emergente en la pantalla
                            Toast.makeText(getApplicationContext(),"Agregue dónde recojerlo",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // Comprobar si el campo descripcionViaje está vacío
                            if (descripcionViaje.getText().toString().isEmpty()){
                                // Mostrar un mensaje de error en el campo descripcionViaje
                                descripcionViaje.setError("Agregue la descripción del viaje");
                                // Mostrar un mensaje emergente en la pantalla
                                Toast.makeText(getApplicationContext(),"Agregue la descripción del viaje",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // Crear una instancia de la clase DeveloperBD2 que gestiona la base de datos local
                                DeveloperBD2 db = new DeveloperBD2(getApplicationContext());
                                // Llamar al método agendarViaje() para guardar los datos del viaje en la base de datos local
                                db.agendarViaje(nombreApellido.getText().toString(), noTelefono.getText().toString(), fecha.getText().toString(), hora.getText().toString(), dondeRecogerlo.getText().toString(), descripcionViaje.getText().toString());
                                // Mostrar un mensaje emergente en la pantalla confirmando que se guardaron los datos
                                Toast.makeText(getApplicationContext(), "Se Agregó correctamente", Toast.LENGTH_SHORT).show();

                                // Obtener los valores de los campos como cadenas usando el método getText() y el método toString()
                                String cadenaNombreApellido = String.valueOf(nombreApellido.getText());
                                String cadenaNoTelefono = String.valueOf(noTelefono.getText());
                                String cadenaFecha = String.valueOf(fecha.getText());
                                String cadenaHora = String.valueOf(hora.getText());
                                String cadenaDondeRecogerlo = String.valueOf(dondeRecogerlo.getText());
                                String cadenaDescripcionViaje = String.valueOf(descripcionViaje.getText());

                                // Crear un mensaje con los datos del viaje usando el operador de concatenación (+)
                                String mensaje = "--Agendar Viaje--\n"+"Nombre: "+cadenaNombreApellido+"\n"+"No.Teléfono: "+cadenaNoTelefono+"\n"+"Fecha: "+cadenaFecha+"\n"+"Hora: "+cadenaHora+"\n"+"Dónde Recogerlo?: "+cadenaDondeRecogerlo+"\n"+"Descripción del viaje: "+cadenaDescripcionViaje;

                                // Crear un intento implícito con la acción Intent.ACTION_VIEW para lanzar una aplicación de mensajes
                                Intent intent = new Intent (Intent.ACTION_VIEW);
                                // Establecer el número de teléfono al que se enviará el mensaje usando el método setData()
                                intent.setData (Uri.parse ("sms:"+ "14671058481"));
                                // Establecer el cuerpo del mensaje usando el método putExtra()
                                intent.putExtra ("sms_body",mensaje);
                                // Iniciar la actividad con el intento
                                startActivity(intent);
                            }

                        }
                    }
                }
            }
        }

    }

    // Este método se ejecuta cuando el usuario pulsa el botón para mostrar el selector de fecha
    public void showDatePicker(View view) {
        // Crear una instancia de la clase DatePickerFragment que muestra un diálogo con un calendario
        DialogFragment newFragment = new DatePickerFragment();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    // Este método se ejecuta cuando el usuario selecciona una fecha en el diálogo
    public void processDatePickerResult(int year, int month, int day) {
        // Convertir los valores numéricos de año, mes y día a cadenas usando el método toString()
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Crear un mensaje con la fecha usando el operador de concatenación (+)
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        // Mostrar un mensaje emergente en la pantalla con la fecha seleccionada
        //Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();
        // Establecer el texto del campo fecha con la fecha seleccionada
        fecha.setText(dateMessage);
    }

    // Este método se ejecuta cuando el usuario pulsa el botón para mostrar el selector de hora
    public void showTimePickerDialog(View v) {
        // Crear una instancia de la clase TimePickerFragment que muestra un diálogo con un reloj
        DialogFragment newFragment = new TimePickerFragment();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // Este método se ejecuta cuando el usuario selecciona una hora en el diálogo
    public void processTimePickerResult(int hour, int minute) {
        // Convertir los valores numéricos de hora y minuto a cadenas usando el método toString()
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        // Crear un mensaje con la hora usando el operador de concatenación (+)
        String dateMessage = (hour_string+":"+minute_string );
        // Establecer el texto del campo hora con la hora seleccionada
        hora.setText(dateMessage);
    }

    // Este método se ejecuta cuando el usuario pulsa el botón para mostrar la agenda de viajes
    public void MostrarAgenda(View view) {
        // Crear un intento explícito para iniciar la actividad MostrarViajesAgendados
        Intent intent = new Intent(AgendarViajeActivity.this, MostrarViajesAgendados.class);
        // Iniciar la actividad con el intento
        startActivity(intent);
    }
}