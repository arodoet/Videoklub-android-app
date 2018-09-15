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

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.adapters.AdapterFilmovi;
import com.example.teodora.videoklub.adapters.AdapterPrethIznajm;
import com.example.teodora.videoklub.model.Film;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrethodnaIznajmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrethodnaIznajmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrethodnaIznajmFragment extends Fragment {


    private static  ArrayList<Film> prethodnaGledanja;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public PrethodnaIznajmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param lista Parameter 1.
     * @return A new instance of fragment PrethodnaIznajmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrethodnaIznajmFragment newInstance(ArrayList<Film> lista) {
        PrethodnaIznajmFragment fragment = new PrethodnaIznajmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, lista.toString());
        fragment.setArguments(args);

        prethodnaGledanja = lista;

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
        View view = inflater.inflate(R.layout.fragment_prethodna_iznajm, container, false);


        // Sada konfigurisem recyclerview i adapter

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_prethodnaiznajm);

        AdapterPrethIznajm adapterPrethIznajm = new AdapterPrethIznajm(prethodnaGledanja);

        recyclerView.addItemDecoration(new Dekorater(30));

        recyclerView.setAdapter(adapterPrethIznajm);

        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());   // ovde sam postavila orijentaciju recyclerview-a - da se vertikalno skroluje
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


class  Dekorater extends RecyclerView.ItemDecoration{

    private int prostor;

    public Dekorater(int i){

        prostor = i;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = prostor;
    }


}
