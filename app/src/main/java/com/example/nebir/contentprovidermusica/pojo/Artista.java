package com.example.nebir.contentprovidermusica.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.nebir.contentprovidermusica.bd.ContratoMusica;

/**
 * Created by nebir on 10/02/2016.
 */
public class Artista {

    private String artista;
    private int id;

    public Artista() {
    }

    public Artista(String artista, int id) {
        this.artista = artista;
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusica.TablaInterprete._ID,this.id);
        cv.put(ContratoMusica.TablaInterprete.ARTISTA,this.artista);
        return cv;
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Artists._ID));
        this.artista = c.getString(c.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
    }

}
