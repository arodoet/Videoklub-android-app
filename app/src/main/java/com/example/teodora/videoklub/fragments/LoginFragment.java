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
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements  VerifikacijaDelegat{ // 3.KORAK:  dodajem implements interfejs da se ne buni u 2.koraku

    private LoginFragmentInteractionListener mListener;


    private EditText korisIme;
    private EditText lozinka;
    private Button loginBtn;

    private ProveraService provera;    //  1. KORAK :   prvo napravim instancu odakle se emituje


    public LoginFragment() {

        provera = new ProveraService(this);    // 2. KORAK-u okviru konstruktora klase inicijalzujem instancu sa ovim THIS - to kaze: U OVOJ KLASI OBRADI; ova klasa je SLUSALAC
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);


        korisIme = (EditText)view.findViewById(R.id.korisIme);
        lozinka = (EditText)view.findViewById(R.id.lozinka);
        loginBtn =(Button)view.findViewById(R.id.login_btn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProveraService.provera(korisIme.getText().toString(), lozinka.getText().toString());
            }
        });

        return view;
    }


    @Override
    public void posaljiTacnost(boolean tacno) {                      // 4. KORAK:   razradim apstraktnu fju od interfejsa
                                                                    // ovde dobijam emitovan podatak iz klase koja je pozvala fju posaljiTacnost a to je ProveraService klasa
        if (tacno) {
            MainActivity.getMainActivity().ucitajGlavnuStranu();
        } else {
            Toast.makeText(getContext(),"Korisničko ime i/ili lozinka nisu ispravni.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onLoginFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentInteractionListener) {
            mListener = (LoginFragmentInteractionListener) context;
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
    public interface LoginFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginFragmentInteraction(Uri uri);
    }


}
