package com.example.teodora.videoklub.activities;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.teodora.videoklub.R;
import com.example.teodora.videoklub.fragments.CaptchaFragment;
import com.example.teodora.videoklub.fragments.GlavniFragment;
import com.example.teodora.videoklub.fragments.ListaFilmovaFragment;
import com.example.teodora.videoklub.fragments.LoginFragment;
import com.example.teodora.videoklub.fragments.MainFragment;
import com.example.teodora.videoklub.fragments.NajpopFilmFragment;
import com.example.teodora.videoklub.fragments.PrethodnaIznajmFragment;
import com.example.teodora.videoklub.fragments.RegistracijaFragment;



public class MainActivity extends AppCompatActivity implements  MainFragment.MainFragmentInteractionListener,LoginFragment.LoginFragmentInteractionListener, GlavniFragment.GlavniFragmentInteractionListener, RegistracijaFragment.RegistracijaFragmentInteractionListener, ListaFilmovaFragment.ListaFilmovaFragmentInteractionListener, NajpopFilmFragment.NajpopFilmFragmentInteractionListener, CaptchaFragment.CaptchaFragmentInteractionListener{



    private static MainActivity mainActivity;            // da bi mogli da pristupimo mainactivity sa bilo kog mesta,da ne pravim stalnu instancu
    public static MainActivity getMainActivity() {return mainActivity;}  //npr. MainActivity.getMainActivity().ucitajGlavnuStranu();
    private static void setMainActivity(MainActivity mainActivity) {  MainActivity.mainActivity = mainActivity; }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setMainActivity(this);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = new CaptchaFragment();
            //fragment = new MainFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    public void ucitajMainFragment(){
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainFragment).addToBackStack(null).commit();
    }

    public void ucitajLoginStranu() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, loginFragment).addToBackStack(null).commit();
    }

    public void ucitajStranuRegistracije() {
        RegistracijaFragment registracijaFragment = new RegistracijaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, registracijaFragment).addToBackStack(null).commit();
    }

    public void ucitajGlavnuStranu() {
        GlavniFragment glavniFragment = new GlavniFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, glavniFragment).addToBackStack(null).commit();
    }

    public void ucitajStranuListaFilmova(ListaFilmovaFragment listaFilmovaFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listaFilmovaFragment).addToBackStack(null).commit();
    }

    public void ucitajStranuNajpopFilm(NajpopFilmFragment najFilmFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, najFilmFragment).addToBackStack(null).commit();
    }

    public void ucitajStranuPrethIznajm(PrethodnaIznajmFragment prethodnaIznajmFragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, prethodnaIznajmFragment).addToBackStack(null).commit();
    }





    @Override
    public void onMainFragmentInteraction(Uri uri) {    //ovo je fja od interfejsa MainFragmentInteractionListener
    }

    @Override
    public void onLoginFragmentInteraction(Uri uri) {    // ovo je fja od interfejsa   LoginFragmentInteractionListener
    }

    @Override
    public void onGlavniFragmentInteraction(Uri uri) {    // ovo je fja od interfejsa  GlavniFragmentInteractionListener
    }

    @Override                                           // ovo je fja od interfejsa RegistracijaFragmentInteractionListener
    public void onRegistracijaFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListaFilmovaFragmentInteraction(Uri uri) {     // ovo je fja od IF  ListaFilmovaFragmentInteractionListener

    }

    @Override
    public void onNajpopFilmFragmentInteraction(Uri uri) {     //fja od IF NajpopFilmFragmentInteractionListener

    }

    @Override
    public void onCaptchaFragmentInteraction(Uri uri) {         //fja od IF CaptchaFragmentInteractionListener

    }
}




