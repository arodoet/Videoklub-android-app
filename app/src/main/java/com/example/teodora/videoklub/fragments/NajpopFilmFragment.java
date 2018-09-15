package com.example.teodora.videoklub.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.activities.MainActivity;
import com.example.teodora.videoklub.model.Film;
import com.example.teodora.videoklub.sevisi.Najpopulaniji;
import com.example.teodora.videoklub.sevisi.ProveraService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NajpopFilmFragment.NajpopFilmFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NajpopFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NajpopFilmFragment extends Fragment {

    TextView imeFilma;
    TextView zanr;
    TextView godina;
    TextView kategorija;
    Button zatvori_btn;


    static Film film;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NajpopFilmFragmentInteractionListener mListener;

    public NajpopFilmFragment() {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NajpopFilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NajpopFilmFragment newInstance(Film param1) {
        NajpopFilmFragment fragment = new NajpopFilmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1.toString());
        fragment.setArguments(args);
        film = param1;
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_najpop_film, container, false);

         imeFilma = (TextView)view.findViewById(R.id.imeFilma);
         zanr = (TextView)view.findViewById(R.id.zanr);
         godina = (TextView)view.findViewById(R.id.godina);
         kategorija = (TextView)view.findViewById(R.id.kategorija);
         zatvori_btn = (Button) view.findViewById(R.id.zatvori_btn);

         imeFilma.setText(film.getNazivFilma());
         zanr.setText(film.getZanr());
         godina.setText(String.valueOf(film.getGodina()));
         kategorija.setText(film.getKategorija());



        zatvori_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getMainActivity().ucitajGlavnuStranu();
            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNajpopFilmFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NajpopFilmFragmentInteractionListener) {
            mListener = (NajpopFilmFragmentInteractionListener) context;
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
    public interface NajpopFilmFragmentInteractionListener {
        // TODO: Update argument type and name
        void onNajpopFilmFragmentInteraction(Uri uri);
    }
}
