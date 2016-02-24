package com.example.nebir.contentprovidermusica.adaptadores;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nebir.contentprovidermusica.R;
import com.example.nebir.contentprovidermusica.bd.ContratoMusica;

import java.io.File;

/**
 * Created by nebir on 16/02/2016.
 */
public class AdaptadorDisco extends RecyclerView.Adapter<AdaptadorDisco.ViewHolder> {

    private Cursor disco;


    public AdaptadorDisco(Cursor c){
        this.disco = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.cursorVH.moveToPosition(position);
        try {
            File imgFile = new File(holder.cursorVH.getString(holder.cursorVH.getColumnIndex("album_art")));

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                holder.imagen.setImageBitmap(myBitmap);

            }
        }catch (RuntimeException e){
            holder.imagen.setImageResource(R.drawable.album);
        };

        holder.tv1.setText(holder.cursorVH.getString(holder.cursorVH.getColumnIndex(ContratoMusica.TablaDisco.DISCO)));
        holder.tv2.setText(holder.cursorVH.getString(holder.cursorVH.getColumnIndex(ContratoMusica.TablaInterprete.ARTISTA)));

    }

    @Override
    public int getItemCount() {
        return disco.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv1;
        public final TextView tv2;
        public final ImageView imagen;
        public Cursor cursorVH;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.cursorVH = disco;
            imagen = (ImageView) view.findViewById(R.id.imagen);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
        }
    }
}
