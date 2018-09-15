package com.example.teodora.videoklub.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.model.Film;

/**
 * Created by Teodora on 6/6/18.
 */

public class FilmViewHolder extends RecyclerView.ViewHolder {

    private TextView nazivFilma;
    private TextView kategorijaFilma;
    private TextView zanrFilma;
    private TextView godinaFilma;




    public FilmViewHolder(View itemView) {
        super(itemView);

        nazivFilma = (TextView)itemView.findViewById(R.id.nazivFilma);
        kategorijaFilma = (TextView)itemView.findViewById(R.id.kategorijaFilma);
        zanrFilma = (TextView)itemView.findViewById(R.id.zanrFilma);
        godinaFilma = (TextView)itemView.findViewById(R.id.godinaFilma);

    }


    public void updateUI(Film film) {

        Log.e("KEY", "FILM JE: " + film.getNazivFilma());

        nazivFilma.setText(film.getNazivFilma());
        kategorijaFilma.setText(film.getKategorija());
        zanrFilma.setText(film.getZanr());
        godinaFilma.setText(String.valueOf(film.getGodina()));
    }
}
