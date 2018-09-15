package com.example.teodora.videoklub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.holders.FilmViewHolder;
import com.example.teodora.videoklub.model.Film;

import java.util.ArrayList;

/**
 * Created by Teodora on 6/5/18.
 */

public class AdapterFilmovi extends RecyclerView.Adapter<FilmViewHolder> {

    // recyclerview - adapter, viewholder , data;      RV ubacim u ListaFilmovaFragment.xml: <include layout>.....
    //ovaj adapter radi logiku: uzima podatke iz izvora i prosledjuje to viewholder-u

    private ArrayList<Film> listaFilmova;

    public AdapterFilmovi(ArrayList<Film> listaFilmova) {

        this.listaFilmova = listaFilmova;
    }




    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_film, parent, false);  //da stvori view iz card layout-a
        return new FilmViewHolder(card);
    }

    @Override
    public int getItemCount() {

        return listaFilmova.size();
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = listaFilmova.get(position);
        holder.updateUI(film);
    }


}
