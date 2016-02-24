package com.example.nebir.contentprovidermusica;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.nebir.contentprovidermusica.adaptadores.AdaptadorArtista;
import com.example.nebir.contentprovidermusica.adaptadores.AdaptadorCancion;
import com.example.nebir.contentprovidermusica.adaptadores.AdaptadorDisco;
import com.example.nebir.contentprovidermusica.bd.ContratoMusica;
import com.example.nebir.contentprovidermusica.bd.MusicaMovil;

public class Principal extends AppCompatActivity {

    private RecyclerView recicler;
    private RecyclerView reciclerDisco;
    private AdaptadorArtista adaptadorArtista;
    private AdaptadorCancion adaptadorCancion;
    private AdaptadorDisco adaptadorDisco;
    private Cursor canciones, artista, disco;
    public static SharedPreferences prefs;
    private MusicaMovil origen;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        recicler = (RecyclerView) findViewById(R.id.recicler);

        prefs = getSharedPreferences("Sincro", Context.MODE_PRIVATE);
        origen = new MusicaMovil();

        if(prefs.getInt("sincro",0)==0) {
            System.out.println("nebi es muy listo pero es un noob");
            origen.sincronizacion(this);
        }

        canciones = this.getContentResolver().query(ContratoMusica.TablaCancion.CONTENT_URI, null, null, null, "title");
        artista = this.getContentResolver().query(ContratoMusica.TablaInterprete.CONTENT_URI,null,null,null,"artist");
        disco = this.getContentResolver().query(ContratoMusica.TablaDisco.CONTENT_URI,null,null,null,"join");

        adaptadorCancion = new AdaptadorCancion(canciones);
        adaptadorArtista = new AdaptadorArtista(artista);
        adaptadorDisco = new AdaptadorDisco(disco);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recicler.setLayoutManager(llm);
        recicler.setAdapter(adaptadorCancion);

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Canciones"));
        tabs.addTab(tabs.newTab().setText("Discos"));
        tabs.addTab(tabs.newTab().setText("Interpretes"));

        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        switch (tabs.getSelectedTabPosition()){
                            case 0:
                                recicler.setAdapter(adaptadorCancion);
                                break;
                            case 1:
                                recicler.setAdapter(adaptadorDisco);
                                break;
                            case 2:
                                recicler.setAdapter(adaptadorArtista);
                                break;
                            default: break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                }
        );
    }

}

