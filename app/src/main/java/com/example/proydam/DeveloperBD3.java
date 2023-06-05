package com.example.proydam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DeveloperBD3 extends SQLiteOpenHelper {
    // Se almacena el nombre de la base de datos
    private static final String NOMBRE_BD = "CarrosMedianosPatula.db";
    // Versión de la base de datos
    private static final int VERSIOM_BD =2;
    // Se crear una tabla con sus respectivos campos
    private static final String TABLA_CARROS_MEDIANOS = "CREATE TABLE CARROS_MEDIANOS(id INTEGER PRIMARY KEY AUTOINCREMENT, MARCA TEXT, MODELO TEXT, AÑO TEXT, TRANSMISION TEXT, NO_PERSONAS TEXT, PRECIO TEXT)";
    //  indicamos que el constructor puede ser accedido desde cualquier otra clase
    public DeveloperBD3(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSIOM_BD);
    }

    //metodo que se ejecutara automaticamente y creara las tablas que conformaran la BD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CARROS_MEDIANOS);
    }

    //este metodo se lanzara unicamente cuando sea necesaria una actualizacion en la estructura de la BD o una conversion de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ("CARROS_MEDIANOS"));
    }

    //método para insertar un carro mediano
    public void insertarCarroMediano(SQLiteDatabase sqLiteDatabase, ContentValues cv){
        sqLiteDatabase.insert("CARROS_MEDIANOS",null, cv);
    }

}
