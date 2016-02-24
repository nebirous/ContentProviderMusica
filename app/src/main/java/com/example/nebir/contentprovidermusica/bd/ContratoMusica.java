package com.example.nebir.contentprovidermusica.bd;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by nebir on 10/02/2016.
 */
public class ContratoMusica {

    private ContratoMusica() {
    }

    public static abstract class TablaCancion implements BaseColumns {

        public static final String TABLACANCION = "Cancion";
        public static final String DATA = "_data";
        public static final String TITULO = "title";
        public static final String ARTISTAID = "artist_id";
        public static final String ALBUMID = "album_id";


        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.nebir.contentprovidermusica.contentProvider.ProveedorMusica";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLACANCION);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLACANCION;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLACANCION;
    }

    public static abstract class TablaDisco implements BaseColumns {
        public static final String  TABLADISCO = "Album";

        public static final String  DISCO = "album";
        public static final String  ARTISTAID = "artist_id";
        public static final String  ALBUMART = "album_art";


        public final static String AUTHORITY = "com.example.nebir.contentprovidermusica.contentProvider.ProveedorMusica";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLADISCO);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLADISCO;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLADISCO;
    }

    public static abstract class TablaInterprete implements BaseColumns {
        public static final String  TABLAINTERPRETE= "Artista";
        public static final String  ARTISTA = "artist";

        public final static String AUTHORITY = "com.example.nebir.contentprovidermusica.contentProvider.ProveedorMusica";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLAINTERPRETE);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLAINTERPRETE;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLAINTERPRETE;

    }

}
