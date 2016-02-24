package com.example.nebir.contentprovidermusica.adaptadores;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.nebir.contentprovidermusica.R;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class AdaptadorCancion extends RecyclerView.Adapter<AdaptadorCancion.ViewHolder> {

    private final Cursor canciones;

    public AdaptadorCancion(Cursor c) {
        canciones = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false);
        System.out.println("ONCREATE AD canc");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        System.out.println("--_ "+canciones.getCount());

        holder.cursorVH.moveToPosition(position);
        holder.tv1.setText(holder.cursorVH.getString(holder.cursorVH.getColumnIndex("title")));

    }

    @Override
    public int getItemCount() {
        return canciones.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv1;
        public Cursor cursorVH;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.cursorVH = canciones;
            tv1 = (TextView) view.findViewById(R.id.tv1);
        }
    }
}
