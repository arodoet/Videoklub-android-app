package com.example.teodora.videoklub.fragments;


import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.adapters.AdapterFilmovi;
import com.example.teodora.videoklub.model.Film;
import com.example.teodora.videoklub.sevisi.ProveraService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFilmovaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFilmovaFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "filmovi";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private ListaFilmovaFragmentInteractionListener mListener;


    private static  ArrayList<Film> skinutiFilmovi;



    public ListaFilmovaFragment( ) {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param filmovi Parameter 1.
     * @return A new instance of fragment ListaFilmovaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFilmovaFragment newInstance(ArrayList<Film> filmovi) {
        ListaFilmovaFragment fragment = new ListaFilmovaFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, filmovi.toString());
        fragment.setArguments(args);

        skinutiFilmovi = filmovi;

        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_filmova, container, false);

        TextView emptyView = (TextView)view.findViewById(R.id.empty_view);  // ovo mi je ako je lista prazna



        // Sada konfigurisem recyclerview i adapter

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_filmovi);




        if (skinutiFilmovi.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }




        AdapterFilmovi adapterFilmovi = new AdapterFilmovi(skinutiFilmovi);

        recyclerView.addItemDecoration(new DekoraterProstoraIzmedjiKartica(30));

        recyclerView.setAdapter(adapterFilmovi);

        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onListaFilmovaFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListaFilmovaFragmentInteractionListener) {
            mListener = (ListaFilmovaFragmentInteractionListener) context;
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
    public interface ListaFilmovaFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListaFilmovaFragmentInteraction(Uri uri);
    }

}

class DekoraterProstoraIzmedjiKartica extends RecyclerView.ItemDecoration{

    private int prostor;

    public DekoraterProstoraIzmedjiKartica(int i){
        prostor = i;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = prostor;
    }
}
