package com.example.proydam;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashMap;

public class DeveloperBD2 extends SQLiteOpenHelper {

    // Se almacena el nombre de la base de datos
    private static final String NOMBRE_BD = "AgendaViajesCarrosPatula.db";
    // Versión de la base de datos
    private static final int VERSIOM_BD =1;
    // Se crear una tabla con sus respectivos campos
    private static final String TABLA_AGENDA_VIAJES = "CREATE TABLE AGENDA_VIAJES(id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE_APELLIDO TEXT, NUMERO_TELEFONO TEXT, FECHA TEXT, HORA TEXT, DONDE_RECOGERLO TEXT, DESCRIPCION_VIAJE TEXT)";
    //  indicamos que el constructor puede ser accedido desde cualquier otra clase
    public DeveloperBD2(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSIOM_BD);
    }

    //metodo que se ejecutara automaticamente y creara las tablas que conformaran la BD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_AGENDA_VIAJES);
    }

    //este metodo se lanzara unicamente cuando sea necesaria una actualizacion en la estructura de la BD o una conversion de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLA_AGENDA_VIAJES);
        sqLiteDatabase.execSQL(TABLA_AGENDA_VIAJES);
    }

    //metodo que agregara los registros dentro de la tabla AGENDA_VIAJES
    public void agendarViaje(String nombreApellido, String telefono, String fecha, String hora, String dondeRecogerlo, String descripcionViaje){

        //Obtener una referencia a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();

        //Crear un objeto ContentValues para almacenar los valores de los campos
        ContentValues values = new ContentValues();

        //Asignar los valores a cada campo usando el nombre de la columna como clave
        values.put("NOMBRE_APELLIDO", nombreApellido);
        values.put("NUMERO_TELEFONO", telefono);
        values.put("FECHA", fecha);
        values.put("HORA", hora);
        values.put("DONDE_RECOGERLO", dondeRecogerlo);
        values.put("DESCRIPCION_VIAJE", descripcionViaje);

        //Insertar los valores en la tabla usando el método insert()
        db.insert("AGENDA_VIAJES", null, values);

        //Cerrar la conexión a la base de datos
        db.close();
    }

}
