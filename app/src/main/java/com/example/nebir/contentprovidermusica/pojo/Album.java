package com.example.nebir.contentprovidermusica.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.nebir.contentprovidermusica.bd.ContratoMusica;

/**
 * Created by nebir on 10/02/2016.
 */
public class Album {
    private String album, albumArt;
    private int id, artistaId;

    public Album() {
    }

    public Album(String album, String albumArt, int id, int artistaId) {
        this.album = album;
        this.albumArt = albumArt;
        this.id = id;
        this.artistaId = artistaId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(int artistaId) {
        this.artistaId = artistaId;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusica.TablaCancion._ID,this.id);
        cv.put(ContratoMusica.TablaDisco.DISCO,this.album);
        cv.put(ContratoMusica.TablaDisco.ALBUMART,this.albumArt);
        cv.put(ContratoMusica.TablaDisco.ARTISTAID,this.artistaId);
        return cv;
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Albums._ID));
        this.album= c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
        this.albumArt = c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
        this.artistaId=c.getInt(c.getColumnIndex("artist_id"));
    }

}
