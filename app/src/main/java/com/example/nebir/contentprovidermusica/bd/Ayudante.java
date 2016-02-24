package com.example.nebir.contentprovidermusica.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nebir on 10/02/2016.
 */

public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "musica.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql = "drop table if exists "
                + ContratoMusica.TablaCancion.TABLACANCION;
        db.execSQL(sql);
        String sq2 = "drop table if exists "
                + ContratoMusica.TablaDisco.TABLADISCO;
        db.execSQL(sq2);
        String sq3 = "drop table if exists "
                + ContratoMusica.TablaInterprete.TABLAINTERPRETE;
        db.execSQL(sq3);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)-
        String sql;
        sql = "create table " + ContratoMusica.TablaCancion.TABLACANCION + " (" +
                ContratoMusica.TablaCancion._ID + " integer primary key, " +
                ContratoMusica.TablaCancion.TITULO + " text, " +
                ContratoMusica.TablaCancion.DATA + " text, " +
                ContratoMusica.TablaCancion.ARTISTAID + " integer, " +
                ContratoMusica.TablaCancion.ALBUMID + " integer)";
        db.execSQL(sql);
        String sq2;
        sq2 = "create table " + ContratoMusica.TablaDisco.TABLADISCO + " (" +
                ContratoMusica.TablaDisco._ID + " integer primary key, " +
                ContratoMusica.TablaDisco.DISCO + " text, " +
                ContratoMusica.TablaDisco.ARTISTAID + " integer, " +
                ContratoMusica.TablaDisco.ALBUMART + " text)";
        db.execSQL(sq2);
        String sq3;
        sq3 = "create table " + ContratoMusica.TablaInterprete.TABLAINTERPRETE + " (" +
                ContratoMusica.TablaInterprete._ID + " integer primary key, " +
                ContratoMusica.TablaInterprete.ARTISTA + " text) ";
        db.execSQL(sq3);
    }
}
