package com.example.proydam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

// Esta clase es una activity que permite al usuario ver y seleccionar un carro mediano
public class CarrosMedianosActivity extends AppCompatActivity {

    // Declarar las variables para los elementos de la interfaz de usuario
    ImageView CarrosMedianos;
    TextView nombreApellido;
    TextView NoTelefono;
    TextView FechaRenta;
    TextView FechaEntrega;
    TextView HoraRenta;
    TextView HoraEntrega;
    TextView TMarca, TModelo, TYear, TTransmision, TPersonas, TPrecio;
    int [] tam_id;

    // Declarar los arreglos para almacenar los datos de los carros medianos
    String [] Marca, Modelo, Year, Transmision, Personas, Precio;
    int cont = 0; // cont para controlar las transiciones de los carros medianos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el layout de la actividad
        setContentView(R.layout.activity_carros_medianos);

        // Obtener las referencias de los elementos de la interfaz de usuario usando el método findViewById()
        CarrosMedianos = findViewById(R.id.imgCarrosMedianos);
        nombreApellido = findViewById(R.id.txtpNombreApellidoRentarAuto);
        NoTelefono = findViewById(R.id.txtpNoTelCamionetasVans);
        FechaRenta = findViewById(R.id.txtpFechaDeRenta);
        FechaEntrega = findViewById(R.id.txtpFechaEntrega);
        HoraRenta = findViewById(R.id.txtpHoraRenta);
        HoraEntrega = findViewById(R.id.txtpHoraEntrega);

        TMarca = findViewById(R.id.txtMarca);
        TModelo = findViewById(R.id.txtModelo);
        TYear = findViewById(R.id.txtAño);
        TTransmision = findViewById(R.id.txtTransmision);
        TPersonas = findViewById(R.id.txtNoPersonas);
        TPrecio = findViewById(R.id.txtNoPrecio);

        // Crear una instancia de la clase DeveloperBD4 que gestiona la base de datos local
        DeveloperBD3 dbHelper = new DeveloperBD3(this);
        // Obtener una referencia a la base de datos en modo lectura
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        // Ejecutar una consulta SQL para obtener todos los datos de la tabla CAMIONETAS_VANS
        Cursor cursor = sqLiteDatabase.rawQuery("select *from CARROS_MEDIANOS",null);

        // si la tabla que contiene camionetas vans esta vacia,  que la llene con los siguientes datos
        if(cursor.getCount()==0){

            // Crear tres objetos ContentValues para almacenar los datos de tres carros medianos
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            ContentValues cv3 = new ContentValues();

            // Poner los valores de la primer carro mediano en el primer objeto ContentValues
            cv1.put("MARCA", "TOYOTA");
            cv1.put("MODELO", "COROLLA");
            cv1.put("AÑO", "2015");
            cv1.put("TRANSMISION", "STANDARD");
            cv1.put("NO_PERSONAS", "5");
            cv1.put("PRECIO", "800 P/24H");

            // Poner los valores de la segundo carro mediano en el segundo objeto ContentValues
            cv2.put("MARCA", "NISSAN");
            cv2.put("MODELO", "VERSA");
            cv2.put("AÑO", "2021");
            cv2.put("TRANSMISION", "STANDARD");
            cv2.put("NO_PERSONAS", "5");
            cv2.put("PRECIO", "800 P/24H");

            // Poner los valores de la tercer carro mediano en el tercero objeto ContentValues
            cv3.put("MARCA", "KIA");
            cv3.put("MODELO", "RIO");
            cv3.put("AÑO", "2018");
            cv3.put("TRANSMISION", "AUTOMATICA");
            cv3.put("NO_PERSONAS", "5");
            cv3.put("PRECIO", "800 P/24H");

            // Llamar al método insertarCarroMediano() para guardar los datos de los tres carros medianos en la base de datos local
            dbHelper.insertarCarroMediano(sqLiteDatabase,cv1);
            dbHelper.insertarCarroMediano(sqLiteDatabase,cv2);
            dbHelper.insertarCarroMediano(sqLiteDatabase,cv3);
        }
        if(cursor.getCount()>0){ // Si la tabla no está vacía

            // Inicializar los arreglos con el número de filas que contiene el cursor
            tam_id = new int[cursor.getCount()];
            Marca = new String[cursor.getCount()];
            Modelo = new String[cursor.getCount()];
            Year = new String[cursor.getCount()];
            Transmision = new String[cursor.getCount()];
            Personas = new String[cursor.getCount()];
            Precio = new String[cursor.getCount()];

            int contador = 0; // Declarar una variable para llevar el control del índice del arreglo

            // Recorrer el cursor mientras haya datos
            while (cursor.moveToNext()){
                // Asignar los valores del cursor a los arreglos correspondientes
                tam_id[contador] = cursor.getInt(0);
                Marca[contador] = cursor.getString(1);
                Modelo[contador] = cursor.getString(2);
                Year[contador] = cursor.getString(3);
                Transmision[contador] = cursor.getString(4);
                Personas[contador] = cursor.getString(5);
                Precio[contador] = cursor.getString(6);
                contador++; // Incrementar en 1 el contador
            }

        }
        // Establecer la imagen de la primera camioneta o van usando el método setImageResource()
        CarrosMedianos.setImageResource(R.drawable.corolla);
        // Establecer los textos de los campos con los datos de la primera camioneta o van usando el método setText()
        TMarca.setText(Marca[0]);
        TModelo.setText(Modelo[0]);
        TYear.setText(Year[0]);
        TTransmision.setText(Transmision[0]);
        TPersonas.setText("Personas: "+Personas[0]);
        TPrecio.setText(Precio[0]);
    }


    // Este método se ejecuta cuando el usuario pulsa el botón siguiente
    public void Siguiente (View view) {
        // Crear una instancia de la clase DeveloperBD3 que gestiona la base de datos local
        DeveloperBD3 dbHelper = new DeveloperBD3(this);
        // Obtener una referencia a la base de datos en modo lectura
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        // Ejecutar una consulta SQL para obtener todos los datos de la tabla CARROS_MEDIANOS
        Cursor cursor = sqLiteDatabase.rawQuery("select *from CARROS_MEDIANOS",null);

        // Comprobar si la tabla tiene datos
        if(cursor.getCount()>0){
            tam_id = new int[cursor.getCount()];
            Marca = new String[cursor.getCount()];
            Modelo = new String[cursor.getCount()];
            Year = new String[cursor.getCount()];
            Transmision = new String[cursor.getCount()];
            Personas = new String[cursor.getCount()];
            Precio = new String[cursor.getCount()];

            int contador = 0; // Declarar una variable para llevar el control del índice del arreglo

            // Recorrer el cursor mientras haya datos
            while (cursor.moveToNext()){
                // Asignar los valores del cursor a los arreglos correspondientes
                tam_id[contador] = cursor.getInt(0);
                Marca[contador] = cursor.getString(1);
                Modelo[contador] = cursor.getString(2);
                Year[contador] = cursor.getString(3);
                Transmision[contador] = cursor.getString(4);
                Personas[contador] = cursor.getString(5);
                Precio[contador] = cursor.getString(6);
                contador++; // Incrementar en 1 el contador
            }

        }

        // Comprobar si se ha llegado al limite de siguiente carro mediano
        if(cont > 2 | cont == 3)
        {
            // Si insiste mostrar que no hay siguiente carro mediano a mostrar
            Toast.makeText(this, "No hay Siguiente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            cont = cont + 1; // Incrementar el índice en 1
            if(cont == 0) // Si el indice es 0, mostrar los datos de la primera carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.corolla); // Establecer la imagen usando el método setImageResource()
                TMarca.setText(Marca[cont]); // Establecer el texto del campo marca usando el método setText()
                TModelo.setText(Modelo[cont]); // Establecer el texto del campo modelo usando el método setText()
                TYear.setText(Year[cont]); // Establecer el texto del campo año usando el método setText()
                TTransmision.setText(Transmision[cont]); // Establecer el texto del campo transmisión usando el método setText()
                TPersonas.setText("Personas: "+Personas[cont]); // Establecer el texto del campo personas usando el método setText()
                TPrecio.setText(Precio[cont]); // Establecer el texto del campo precio usando el método setText()
            }
            if(cont == 1) // Si el indice es 1, mostrar los datos del segundo carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.versa);
                TMarca.setText(Marca[cont]);
                TModelo.setText(Modelo[cont]);
                TYear.setText(Year[cont]);
                TTransmision.setText(Transmision[cont]);
                TPersonas.setText("Personas: "+Personas[cont]);
                TPrecio.setText(Precio[cont]);
            }
            if(cont == 2) // Si el indice es 2, mostrar los datos del tercer carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.rio);
                TMarca.setText(Marca[cont]);
                TModelo.setText(Modelo[cont]);
                TYear.setText(Year[cont]);
                TTransmision.setText(Transmision[cont]);
                TPersonas.setText("Personas: "+Personas[cont]);
                TPrecio.setText(Precio[cont]);
            }
        }

    }
    /////////////////////////////////////////////////////////////////////////////////
    // Comprobar si se ha llegado al limite de anterior carro mediano
    public void Anterior (View view) {
        // Comprobar si se ha llegado al limite de anterior carro mediano a mostrar
        if (cont < 0) {
            // Si insiste mostrar que no hay siguiente camioneta o van a mostrar
            Toast.makeText(this, "No hay Anterior", Toast.LENGTH_SHORT).show();
        }
        else
        {
            cont = cont - 1; // Decrementar el índice en 1
            if (cont == 0) // Si el indice es 0, mostrar los datos de la primera carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.corolla);
                TMarca.setText(Marca[cont]);
                TModelo.setText(Modelo[cont]);
                TYear.setText(Year[cont]);
                TTransmision.setText(Transmision[cont]);
                TPersonas.setText("Personas: "+Personas[cont]);
                TPrecio.setText(Precio[cont]);
            }
            if (cont == 1) // Si el indice es 1, mostrar los datos del segundo carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.versa);
                TMarca.setText(Marca[cont]);
                TModelo.setText(Modelo[cont]);
                TYear.setText(Year[cont]);
                TTransmision.setText(Transmision[cont]);
                TPersonas.setText("Personas: "+Personas[cont]);
                TPrecio.setText(Precio[cont]);
            }
            if (cont == 2) // Si el indice es 2, mostrar los datos del tercer carro mediano
            {
                CarrosMedianos.setImageResource(R.drawable.rio);
                TMarca.setText(Marca[cont]);
                TModelo.setText(Modelo[cont]);
                TYear.setText(Year[cont]);
                TTransmision.setText(Transmision[cont]);
                TPersonas.setText("Personas: "+Personas[cont]);
                TPrecio.setText(Precio[cont]);
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    // Este método se ejecuta cuando el usuario pulsa el botón enviar
    public void EnviarRenta (View view){
        //Checa si TextView esta vacio
        if (nombreApellido.getText().toString().isEmpty()){
            //Avisar que falta de llenar ese campo
            nombreApellido.setError("Agregue su nombre y apellido");
            Toast.makeText(getApplicationContext(),"Agregue su nombre y apellido",Toast.LENGTH_SHORT).show();
        }else{
            //Checa si TextView esta vacio
            if (NoTelefono.getText().toString().isEmpty()){
                //Avisar que falta de llenar ese campo
                NoTelefono.setError("Agregue su número de teléfono");
                Toast.makeText(getApplicationContext(),"Agregue su número de teléfono",Toast.LENGTH_SHORT).show();
            }
            else{
                //Checa si TextView esta vacio
                if (FechaRenta.getText().toString().isEmpty()){
                    //Avisar que falta de llenar ese campo
                    FechaRenta.setError("Agregue la fecha de renta");
                    Toast.makeText(getApplicationContext(),"Agregue la fecha de renta",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Checa si TextView esta vacio
                    if (HoraRenta.getText().toString().isEmpty()){
                        // Quitar el error
                        FechaRenta.setError(null);
                        // Quitar el foco
                        FechaRenta.clearFocus();
                        //Avisar que falta de llenar ese campo
                        HoraRenta.setError("Agregue la hora de renta");
                        Toast.makeText(getApplicationContext(),"Agregue la hora de renta",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Checa si TextView esta vacio
                        if (FechaEntrega.getText().toString().isEmpty()){
                            // Quitar el error
                            HoraRenta.setError(null);
                            // Quitar el foco
                            HoraRenta.clearFocus();
                            //Avisar que falta de llenar ese campo
                            FechaEntrega.setError("Agregue la fecha de entrega");
                            Toast.makeText(getApplicationContext(),"Agregue la fecha de entrega",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Checa si TextView esta vacio
                            if (HoraEntrega.getText().toString().isEmpty()){
                                // Quitar el error
                                FechaEntrega.setError(null);
                                // Quitar el foco
                                FechaEntrega.clearFocus();
                                //Avisar que falta de llenar ese campo
                                HoraEntrega .setError("Agregue la hora de entrega");
                                Toast.makeText(getApplicationContext(),"Agregue la hora de entrega",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // Quitar el error
                                HoraEntrega.setError(null);
                                // Quitar el foco
                                HoraEntrega.clearFocus();

                                //asignamos los modelos de los carros medianos a su respectiva variable String
                                String carro0 = "Corolla";
                                String carro1 = "Versa";
                                String carro2 = "Rio";
                                //String para asignar la camioneta seleccionada
                                String carroSeleccionado = null;
                                //Depende donde quede el contador del control de transicion, asignar la reséctiva camioneta van a camionetaSeleccionada
                                if(cont == 0) {
                                    carroSeleccionado = carro0;
                                }
                                if (cont == 1){
                                    carroSeleccionado = carro1;
                                }
                                if (cont == 2){
                                    carroSeleccionado = carro2;
                                }

                                // Obtener los valores de los campos como cadenas usando el método getText() y el método toString()
                                String cadenaNombreApellido = String.valueOf(nombreApellido.getText());
                                String cadenaNoTelefono = String.valueOf(NoTelefono.getText());
                                String cadenaFechaRenta = String.valueOf(FechaRenta.getText());
                                String cadenaHoraRenta = String.valueOf(HoraRenta.getText());
                                String cadenaFechaEntrega = String.valueOf(FechaEntrega.getText());
                                String cadenaHoraEntrega = String.valueOf(HoraEntrega.getText());

                                // Crear un mensaje con los datos del viaje usando el operador de concatenación (+)
                                String mensaje = "--Renta de Auto--\n"+carroSeleccionado+"\n"+"Nombre: "+cadenaNombreApellido+"\n"+"No.Teléfono: "+cadenaNoTelefono+"\n"+"Fecha de renta: "+cadenaFechaRenta+"\n"+"Hora de renta: "+cadenaHoraRenta+"\n"+"Fecha de entrega: "+cadenaFechaEntrega+"\n"+"Hora de Entrega: "+cadenaHoraEntrega;

                                // Crear un intento implícito con la acción Intent.ACTION_VIEW para lanzar una aplicación de mensajes
                                Intent intent = new Intent (Intent.ACTION_VIEW);
                                // Establecer el número de teléfono al que se enviará el mensaje usando el método setData()
                                intent.setData (Uri.parse ("sms:"+ "14671058481"));
                                // Establecer el cuerpo del mensaje usando el método putExtra()
                                intent.putExtra ("sms_body",mensaje);
                                // Iniciar la actividad con el intent
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    // Date picker Fecha de renta (4)
    public void showDatePicker4(View view) {
        // Crear una instancia de la clase DatePickerFragment que muestra un diálogo con un calendario
        DialogFragment newFragment = new DatePickerFragment4();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    // Este método se ejecuta cuando el usuario selecciona una fecha en el diálogo
    public void processDatePickerResult4(int year, int month, int day) {
        // Convertir los valores numéricos de año, mes y día a cadenas usando el método toString()
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Crear un mensaje con la fecha usando el operador de concatenación (+)
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        // Establecer el texto del campo fecha con la fecha seleccionada
        FechaRenta.setText(dateMessage);
    }
    //////////////////////////////////////////////////////////////////////////////////
    // Date picker Fecha de Entrega (5)
    public void showDatePicker5(View view) {
        // Crear una instancia de la clase DatePickerFragment que muestra un diálogo con un calendario
        DialogFragment newFragment = new DatePickerFragment5();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    // Este método se ejecuta cuando el usuario selecciona una fecha en el diálogo
    public void processDatePickerResult5(int year, int month, int day) {
        // Convertir los valores numéricos de año, mes y día a cadenas usando el método toString()
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Crear un mensaje con la fecha usando el operador de concatenación (+)
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        // Establecer el texto del campo fecha con la fecha seleccionada
        FechaEntrega.setText(dateMessage);
    }
    //////////////////////////////////////////////////////////////////////////////////
    // Time picker Hora Renta (4)
    public void showTimePickerDialog4(View v) {
        // Crear una instancia de la clase TimePickerFragment que muestra un diálogo con un reloj
        DialogFragment newFragment = new TimePickerFragment4();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    // Este método se ejecuta cuando el usuario selecciona una hora en el diálogo
    public void processTimePickerResult4(int hour, int minute) {
        // Convertir los valores numéricos de hora y minuto a cadenas usando el método toString()
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        // Crear un mensaje con la hora usando el operador de concatenación (+)
        String dateMessage = (hour_string+":"+minute_string );
        // Establecer el texto del campo hora con la hora seleccionada
        HoraRenta.setText(dateMessage);
    }
    //////////////////////////////////////////////////////////////////////////////////
    // Time picker Hora Renta (5)
    public void showTimePickerDialog5(View v) {
        // Crear una instancia de la clase TimePickerFragment que muestra un diálogo con un reloj
        DialogFragment newFragment = new TimePickerFragment5();
        // Mostrar el diálogo usando el método show() y pasando el gestor de fragmentos y una etiqueta
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    // Este método se ejecuta cuando el usuario selecciona una hora en el diálogo
    public void processTimePickerResult5(int hour, int minute) {
        // Convertir los valores numéricos de hora y minuto a cadenas usando el método toString()
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        // Crear un mensaje con la hora usando el operador de concatenación (+)
        String dateMessage = (hour_string+":"+minute_string );
        // Establecer el texto del campo hora con la hora seleccionada
        HoraEntrega.setText(dateMessage);
    }
}