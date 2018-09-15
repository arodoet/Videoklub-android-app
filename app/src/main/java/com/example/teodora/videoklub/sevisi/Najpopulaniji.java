package com.example.teodora.videoklub.sevisi;

import com.example.teodora.videoklub.model.Film;

import java.util.ArrayList;

/**
 * Created by Teodora on 6/9/18.
 */

public interface Najpopulaniji {

    public void najpop(Film f);

    public void download(ArrayList<Film> lista);

    public void downloadPrethodnihFilmova(ArrayList<Film> lista);

}
