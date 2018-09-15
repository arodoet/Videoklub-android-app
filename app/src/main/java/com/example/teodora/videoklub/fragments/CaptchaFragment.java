package com.example.teodora.videoklub.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.activities.MainActivity;
import com.example.teodora.videoklub.model.Captcha;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaptchaFragment.CaptchaFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaptchaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaptchaFragment extends Fragment {

    ImageView slika;
    EditText kepcaKorisnika;
    Button potvrdi_btn;
    Captcha kepca;




    private CaptchaFragmentInteractionListener mListener;

    public CaptchaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaptchaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaptchaFragment newInstance(String param1, String param2) {
        CaptchaFragment fragment = new CaptchaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_captcha, container, false);

        slika = (ImageView)view.findViewById(R.id.kepca_img);
        kepcaKorisnika = (EditText)view.findViewById(R.id.kepcaKorisnika);
        potvrdi_btn = (Button)view.findViewById(R.id.potvrdi_btn);


        kepca = new Captcha(300, 100, 5, Captcha.OpcijeTeksta.UPPERCASE_ONLY);


        slika.setImageBitmap(kepca.getSlika());

        potvrdi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!kepca.proveriOdgovor(kepcaKorisnika.getText().toString().trim())){
                    Toast.makeText(getContext(), "GRESKA", Toast.LENGTH_SHORT).show();
                }
                else {
                    MainActivity.getMainActivity().ucitajMainFragment();
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCaptchaFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CaptchaFragmentInteractionListener) {
            mListener = (CaptchaFragmentInteractionListener) context;
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
    public interface CaptchaFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCaptchaFragmentInteraction(Uri uri);
    }
}
