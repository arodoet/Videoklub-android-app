package com.example.teodora.videoklub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.holders.FilmViewHolder;
import com.example.teodora.videoklub.holders.PrethIznajmViewHolder;
import com.example.teodora.videoklub.model.Film;

import java.util.ArrayList;

/**
 * Created by Teodora on 6/9/18.
 */

public class AdapterPrethIznajm extends RecyclerView.Adapter<PrethIznajmViewHolder> {


    private ArrayList<Film> listaFilmova;

    public AdapterPrethIznajm(ArrayList<Film> listaFilmova) {

        this.listaFilmova = listaFilmova;
    }




    @Override
    public PrethIznajmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prethodna_iznajm, parent, false);  //da stvori view iz card layout-a
        return new PrethIznajmViewHolder(card);
    }

    @Override
    public int getItemCount() {

        return listaFilmova.size();
    }

    @Override
    public void onBindViewHolder(PrethIznajmViewHolder holder, int position) {
        Film film = listaFilmova.get(position);
        holder.updateUI(film);
    }

}
