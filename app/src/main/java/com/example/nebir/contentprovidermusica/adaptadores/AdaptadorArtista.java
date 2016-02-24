package com.example.nebir.contentprovidermusica.adaptadores;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nebir.contentprovidermusica.R;
import com.example.nebir.contentprovidermusica.bd.ContratoMusica;


public class AdaptadorArtista extends RecyclerView.Adapter<AdaptadorArtista.ViewHolder> {

    private Cursor artistas;

    public AdaptadorArtista(Cursor artista) {
        this.artistas = artista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.artista.moveToPosition(position);
        holder.artistaNombre.setText(holder.artista.getString(holder.artista.getColumnIndex(ContratoMusica.TablaInterprete.ARTISTA)));
    }

    @Override
    public int getItemCount() {
        return artistas.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView artistaNombre;
        public Cursor artista;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            artista=artistas;
            artistaNombre = (TextView) view.findViewById(R.id.tv1);
        }

    }
}
