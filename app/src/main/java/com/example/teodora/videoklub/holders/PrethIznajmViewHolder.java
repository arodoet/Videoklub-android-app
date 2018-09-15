package com.example.teodora.videoklub.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.model.Film;

/**
 * Created by Teodora on 6/9/18.
 */

public class PrethIznajmViewHolder extends RecyclerView.ViewHolder {

    private TextView nazivFilma_;
    private TextView kategorijaFilma_;
    private TextView zanrFilma_;
    private TextView godinaFilma_;




    public PrethIznajmViewHolder(View itemView) {
        super(itemView);

        nazivFilma_ = (TextView)itemView.findViewById(R.id.nazivFilma_);
        kategorijaFilma_ = (TextView)itemView.findViewById(R.id.kategorijaFilma_);
        zanrFilma_ = (TextView)itemView.findViewById(R.id.zanrFilma_);
        godinaFilma_ = (TextView)itemView.findViewById(R.id.godinaFilma_);

    }


    public void updateUI(Film film) {

        Log.e("KEY", "FILM JE: " + film.getNazivFilma());

        nazivFilma_.setText(film.getNazivFilma());
        kategorijaFilma_.setText(film.getKategorija());
        zanrFilma_.setText(film.getZanr());
        godinaFilma_.setText(String.valueOf(film.getGodina()));
    }
}
