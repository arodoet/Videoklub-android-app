package com.example.teodora.videoklub.fragments;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.activities.MainActivity;
import com.example.teodora.videoklub.model.Film;
import com.example.teodora.videoklub.sevisi.Najpopulaniji;
import com.example.teodora.videoklub.sevisi.ProveraService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GlavniFragment.GlavniFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GlavniFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GlavniFragment extends Fragment implements Najpopulaniji{


    private ProveraService provera;



    private Button lista_btn;
    private Button najpopularniji_btn;
    private Button rezervacija_btn;
    private Button prethodnaIznajmljivanja;


    private GlavniFragmentInteractionListener mListener;

    public GlavniFragment() {
        provera = new ProveraService(this);  // OVAJ CE DA SLUSA
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GlavniFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlavniFragment newInstance(String param1, String param2) {
        GlavniFragment fragment = new GlavniFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_glavni, container, false);

        lista_btn = (Button)view.findViewById(R.id.lista_btn);
        najpopularniji_btn = (Button)view.findViewById(R.id.najpopularniji_btn);
        prethodnaIznajmljivanja = (Button)view.findViewById(R.id.prethodnaIznajm_btn);
        rezervacija_btn = (Button)view.findViewById(R.id.rezervacija_btn);


        lista_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProveraService.skidanjeFilmovaSaServera();
            }
        });

        najpopularniji_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProveraService.getNajpopFilm();
            }
        });

        prethodnaIznajmljivanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProveraService.prethodniFilmovi();
            }
        });

        rezervacija_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(MainActivity.getMainActivity().getFragmentManager() , "DialogFragment");

            }
        });


        return view;
    }

    @Override
    public void najpop(Film f) {

        NajpopFilmFragment najpopFilm = NajpopFilmFragment.newInstance(f);
        MainActivity.getMainActivity().ucitajStranuNajpopFilm(najpopFilm);

    }

    @Override
    public void download(ArrayList<Film> lista) {
        // ovde dobijem listu filmova


        ListaFilmovaFragment listaFilmovaFragment = ListaFilmovaFragment.newInstance(lista);

        MainActivity.getMainActivity().ucitajStranuListaFilmova(listaFilmovaFragment);

    }

    @Override
    public void downloadPrethodnihFilmova(ArrayList<Film> lista) {

        PrethodnaIznajmFragment prethodnaIznajmFragment = PrethodnaIznajmFragment.newInstance(lista);

        MainActivity.getMainActivity().ucitajStranuPrethIznajm(prethodnaIznajmFragment);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onGlavniFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GlavniFragmentInteractionListener) {
            mListener = (GlavniFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface GlavniFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGlavniFragmentInteraction(Uri uri);
    }
}
