package com.example.nebir.contentprovidermusica.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.nebir.contentprovidermusica.bd.ContratoMusica;

/**
 * Created by nebir on 10/02/2016.
 */
public class Cancion {

    private String _data, title;
    private int id, albumid, artistaid;

    public Cancion() {
        this("","",0,0,0);
    }

    public Cancion(String _data, String title, int id, int albumid, int artistaid) {
        this._data = _data;
        this.title = title;
        this.id = id;
        this.albumid = albumid;
        this.artistaid = artistaid;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public int getArtistaid() {
        return artistaid;
    }

    public void setArtistaid(int artistaid) {
        this.artistaid = artistaid;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusica.TablaCancion._ID,this.id);
        cv.put(ContratoMusica.TablaCancion.DATA,this._data);
        cv.put(ContratoMusica.TablaCancion.TITULO,this.title);
        cv.put(ContratoMusica.TablaCancion.ARTISTAID,this.artistaid);
        cv.put(ContratoMusica.TablaCancion.ALBUMID,this.albumid);
        return cv;
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Media._ID));
        this._data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
        this.title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
        this.artistaid=c.getInt(c.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        this.albumid=c.getInt(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
    }

}
