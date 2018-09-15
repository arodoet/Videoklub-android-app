package com.example.teodora.videoklub.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Teodora on 6/10/18.
 */

public class DialogFragment extends android.app.DialogFragment implements VerifikacijaDelegat{


    private ProveraService prijem;


    EditText upisanFilm;
    Button rezervisi_btn;
    Button zatvori_btn;


    public DialogFragment(){
        prijem = new ProveraService(this);
    }


    @Override
    public void posaljiTacnost(boolean tacno) {
        if (tacno){
            Toast.makeText(MainActivity.getMainActivity().getApplicationContext(), "Uspešna rezervacija.", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(MainActivity.getMainActivity().getApplicationContext(), "Nespešna rezervacija.", Toast.LENGTH_SHORT).show();

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.prozor, container, false);


        upisanFilm = (EditText)view.findViewById(R.id.upisanFilm);
        rezervisi_btn = (Button)view.findViewById(R.id.rezervisi_btn);
        zatvori_btn = (Button)view.findViewById(R.id.zatvori_btn);


        zatvori_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

            }
        });


        rezervisi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String naziv = upisanFilm.getText().toString().replace(" ","%20");   // %20 se koristi za razmak u url-u

                if (!naziv.equals("")) {

                    ProveraService.rezervisi(naziv);

                } else {
                    Toast.makeText(MainActivity.getMainActivity().getApplicationContext(), "Unesite ime filma.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }
}
