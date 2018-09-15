package com.example.teodora.videoklub.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.activities.MainActivity;
import com.example.teodora.videoklub.sevisi.ProveraService;
import com.example.teodora.videoklub.sevisi.VerifikacijaDelegat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistracijaFragment.RegistracijaFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistracijaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistracijaFragment extends Fragment implements VerifikacijaDelegat{

    private RegistracijaFragmentInteractionListener mListener;


    private EditText imeClana;
    private EditText prezimeClana;
    private EditText korisImeClana;
    private EditText lozinkaClana;
    private Button registracija;
    private Button sledecaStrana;

    private ProveraService provera;

    public RegistracijaFragment() {
        provera = new ProveraService(this); // ovde kazem da je ova klasa SLUSALAC onog sto emituje ProveraService kad uradi sta treba za registraciju;  idi na posaljiTacnost() metodu
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistracijaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistracijaFragment newInstance(String param1, String param2) {
        RegistracijaFragment fragment = new RegistracijaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registracija, container, false);

        imeClana = (EditText)view.findViewById(R.id.ime_clana);
        prezimeClana = (EditText)view.findViewById(R.id.prezime_clana);
        korisImeClana = (EditText)view.findViewById(R.id.korisIme_clana);
        lozinkaClana = (EditText)view.findViewById(R.id.lozinka_clana);
        registracija = (Button)view.findViewById(R.id.registracija_btn);
        sledecaStrana = (Button)view.findViewById(R.id.sledecaStrana_btn);

        sledecaStrana.setEnabled(false);
        sledecaStrana.getBackground().setAlpha(128);



        registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProveraService.sacuvaj(imeClana.getText().toString(), prezimeClana.getText().toString(), korisImeClana.getText().toString(), lozinkaClana.getText().toString());

            }
        });

        sledecaStrana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getMainActivity().ucitajGlavnuStranu();

            }
        });

        return view;
    }

    @Override
    public void posaljiTacnost(boolean tacno) {    //ovo je metoda od mog interfejsa

        if(tacno){
            Toast.makeText(getContext(),"Uspesna registracija",Toast.LENGTH_SHORT).show();
            sledecaStrana.setEnabled(true);
            sledecaStrana.getBackground().setAlpha(255);
        }
        else {
            Toast.makeText(getContext(),"Uneto korisnicko ime je zauzeto.",Toast.LENGTH_SHORT).show();
        }
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onRegistracijaFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistracijaFragmentInteractionListener) {
            mListener = (RegistracijaFragmentInteractionListener) context;
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
    public interface RegistracijaFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRegistracijaFragmentInteraction(Uri uri);
    }
}
