package com.example.nebir.contentprovidermusica.bd;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.nebir.contentprovidermusica.Principal;
import com.example.nebir.contentprovidermusica.pojo.Album;
import com.example.nebir.contentprovidermusica.pojo.Artista;
import com.example.nebir.contentprovidermusica.pojo.Cancion;

/**
 * Created by nebir on 12/02/2016.
 */
public class MusicaMovil {

    public void sincronizacion(Context context){
        sacarMusica(context);
        sacarAlbum(context);
        sacarArtista(context);
        SharedPreferences.Editor editor = Principal.prefs.edit();
        editor.putInt("sincro", 1);
        editor.commit();
    }

    private void sacarMusica(Context context) {
        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.IS_MUSIC+ " = 1", null, null);
        cur.moveToNext();
        while (cur.moveToNext()) {
            Cancion cancion = new Cancion();
            cancion.setCursorBack(cur);
            ContentValues cv = cancion.getContentValues();
            context.getContentResolver().insert(ContratoMusica.TablaCancion.CONTENT_URI, cv);
        }
        cur.close();
    }

    private void sacarAlbum(Context context) {
        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            Album album=new Album();
            album.setCursorBack(cur);
            ContentValues cv= album.getContentValues();
            context.getContentResolver().insert(ContratoMusica.TablaDisco.CONTENT_URI, cv);
        }
        cur.close();
    }

    private void sacarArtista(Context context) {

        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);

        while (cur.moveToNext()) {
            Artista artista= new Artista();
            artista.setCursorBack(cur);
            ContentValues cv = artista.getContentValues();
            context.getContentResolver().insert(ContratoMusica.TablaInterprete.CONTENT_URI, cv);
        }
        cur.close();
    }

}
