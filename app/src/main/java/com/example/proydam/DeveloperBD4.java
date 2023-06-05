package com.example.proydam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DeveloperBD4 extends SQLiteOpenHelper {
    // Se almacena el nombre de la base de datos
    private static final String NOMBRE_BD = "CamionetasVans.db";
    // Versión de la base de datos
    private static final int VERSIOM_BD =1;
    // Se crear una tabla con sus respectivos campos
    private static final String TABLA_CAMIONETAS_VANS = "CREATE TABLE CAMIONETAS_VANS(id INTEGER PRIMARY KEY AUTOINCREMENT, MARCA TEXT, MODELO TEXT, AÑO TEXT, TRANSMISION TEXT, NO_PERSONAS TEXT, PRECIO TEXT)";
    //  indicamos que el constructor puede ser accedido desde cualquier otra clase
    public DeveloperBD4(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSIOM_BD);
    }

    //metodo que se ejecutara automaticamente y creara las tablas que conformaran la BD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CAMIONETAS_VANS);
    }

    //este metodo se lanzara unicamente cuando sea necesaria una actualizacion en la estructura de la BD o una conversion de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ("CAMIONETAS_VANS"));
    }

    //método para insertar un carro mediano
    public void insertarCamioneta(SQLiteDatabase sqLiteDatabase, ContentValues cv){
        sqLiteDatabase.insert("CAMIONETAS_VANS",null, cv);
    }

}