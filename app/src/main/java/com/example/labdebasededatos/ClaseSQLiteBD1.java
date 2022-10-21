package com.example.labdebasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory; import android.database.sqlite.SQLiteOpenHelper;

public class ClaseSQLiteBD1 extends SQLiteOpenHelper{
    public static final String DataBaseName="Estudiantes.db"; public static final int dbversion=1;

    public ClaseSQLiteBD1(Context context, String name, CursorFactory factory, int version) {
        super(context, DataBaseName, factory, dbversion);
// TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
//se ejecuta la primera vez para crear la BD
        db.execSQL("Create table contacto (_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, CORREO TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub db.execSQL("DROP TABLE IF EXISTS contacto;"); onCreate(db);
    }
}
