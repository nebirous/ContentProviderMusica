package com.example.nebir.contentprovidermusica.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nebir.contentprovidermusica.bd.Ayudante;
import com.example.nebir.contentprovidermusica.bd.ContratoMusica;

import java.io.IOException;

/**
 * Created by nebir on 10/02/2016.
 */
public class ProveedorMusica extends ContentProvider {

    public static final UriMatcher uriSwitch;
    public static final int CANCION = 1;
    public static final int DISCO = 2;
    public static final int INTERPRETE = 3;

    static{
        uriSwitch = new UriMatcher(UriMatcher.NO_MATCH);
        uriSwitch.addURI(ContratoMusica.TablaCancion.AUTHORITY, ContratoMusica.TablaCancion.TABLACANCION, CANCION);
        uriSwitch.addURI(ContratoMusica.TablaDisco.AUTHORITY, ContratoMusica.TablaDisco.TABLADISCO, DISCO);
        uriSwitch.addURI(ContratoMusica.TablaInterprete.AUTHORITY, ContratoMusica.TablaInterprete.TABLAINTERPRETE, INTERPRETE);
    }

    private Ayudante adb;

    @Override
    public boolean onCreate() {
        adb = new Ayudante(getContext());
        adb.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        SQLiteDatabase db = adb.getReadableDatabase();
        // Comparar Uri
        int match = uriSwitch.match(uri);

        Cursor c;

        switch (match) {
            case CANCION:
                // Consultando todos los registros
                c = db.query(ContratoMusica.TablaCancion.TABLACANCION, projection, selection, selectionArgs, null, null, sortOrder);
                System.out.println("allah es grande "+c);
                System.out.println("allah mola mazo "+c.getCount());
                break;
            case DISCO:
                // Consultando todos los registros
                if (sortOrder!="join"){
                    c = db.query(ContratoMusica.TablaDisco.TABLADISCO, projection, selection, selectionArgs, null, null, sortOrder);
                }else{
                    String s="Select * from album LEFT JOIN artista ON (album.artist_id = artista._id) order by album";
                    c=db.rawQuery(s,null);
                }

                break;
            case INTERPRETE:
                // Consultando todos los registros
                c = db.query(ContratoMusica.TablaInterprete.TABLAINTERPRETE, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        switch (match) {
            case CANCION:
                c.setNotificationUri(getContext().getContentResolver(), ContratoMusica.TablaCancion.CONTENT_URI);
                break;
            case DISCO:
                c.setNotificationUri(getContext().getContentResolver(),ContratoMusica.TablaDisco.CONTENT_URI);
                break;
            case INTERPRETE:
                c.setNotificationUri(getContext().getContentResolver(),ContratoMusica.TablaInterprete.CONTENT_URI);
                break;
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(uriSwitch.match(uri)){
            case CANCION:
                return ContratoMusica.TablaCancion.MJLTIPLE_MIME;
            case DISCO:
                return ContratoMusica.TablaDisco.MJLTIPLE_MIME;
            case INTERPRETE:
                return ContratoMusica.TablaInterprete.MJLTIPLE_MIME;
            default:
                throw new IllegalArgumentException("Mal " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Resultados null ");
        }

        if (uriSwitch.match(uri) != CANCION && uriSwitch.match(uri) != DISCO && uriSwitch.match(uri) != INTERPRETE) {
            throw new IllegalArgumentException("URI desconocida : " + uri + uriSwitch.match(uri) );
        }

        SQLiteDatabase database = adb.getWritableDatabase();

        long idLinea;

        switch (uriSwitch.match(uri)){
            case CANCION:
                idLinea = database.insert(ContratoMusica.TablaCancion.TABLACANCION, null, values);

                if(idLinea>0){
                    Uri uriActividad = ContentUris.withAppendedId(ContratoMusica.TablaCancion.CONTENT_URI, idLinea);
                    getContext().getContentResolver().notifyChange(uriActividad, null);

                    return uriActividad;
                }else{
                    try {
                        throw new IOException("Error insertar cancion: "+ uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            case DISCO:
                idLinea = database.insert(ContratoMusica.TablaDisco.TABLADISCO, null, values);

                if(idLinea>0){
                    Uri uriActividad = ContentUris.withAppendedId(ContratoMusica.TablaDisco.CONTENT_URI, idLinea);
                    getContext().getContentResolver().notifyChange(uriActividad, null);

                    return uriActividad;
                }else{
                    try {
                        throw new IOException("Error insertar disco: "+ uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            case INTERPRETE:
                idLinea = database.insert(ContratoMusica.TablaInterprete.TABLAINTERPRETE, null, values);

                if(idLinea>0){
                    Uri uriActividad = ContentUris.withAppendedId(ContratoMusica.TablaInterprete.CONTENT_URI, idLinea);
                    getContext().getContentResolver().notifyChange(uriActividad, null);

                    return uriActividad;
                }else{
                    try {
                        throw new IOException("Error insertar interprete: "+ uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            default:
                return null;
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {


        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
