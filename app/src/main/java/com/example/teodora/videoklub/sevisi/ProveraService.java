package com.example.teodora.videoklub.sevisi;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.teodora.videoklub.activities.MainActivity;
import com.example.teodora.videoklub.model.Film;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Teodora on 6/7/18.
 */

public class ProveraService {

    private static Najpopulaniji filmovi;


    private static  Najpopulaniji naj;
    public ProveraService(Najpopulaniji f){

        naj = f;

        filmovi = f;
    }





    private static VerifikacijaDelegat delegat;        // ovo mora kod delegPaterna:  deklarisem promenljivu koja ce biti tipa mog interfejsa VerifikacijaDelegat
    public ProveraService(VerifikacijaDelegat vd) {    // i onda se mora napraviti konstruktor koji kao parametar  prima promenljivu tipa ovog if
        delegat = vd;
    }



    public static ArrayList<Film> filmoviSaServera;

    public static int brojClana;


    public static void provera(final String korisIme, final String lozinka){


        // PROMENI IP ADRESUUU ! ! !



        String  url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/prijavaa/%22" + korisIme + "%22/%22" + lozinka + "%22";    // %22 je zamena za " u url-u

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());

        final JsonObjectRequest zahtev = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("idClana") != 0) {

                                brojClana = response.getInt("idClana");

                                delegat.posaljiTacnost(true);                                         //  OVAJ OVDE EMITUJE  DA JE TRUE

                                Log.e("BROJ CLANA je", String.valueOf(brojClana));

                            } else {

                                delegat.posaljiTacnost(false);                                         // OVAJ EMITUJE DA JE FALSE

                                Log.e("ODGOVOR", "TAKAV CLAN NE POSTOJIIII");
                            }

                        } catch (JSONException e) {
                            Log.e("Rest odgovor PROVERE","ERR: " + e.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest odgovor PROVERE", error.toString());
                    }
                }
        );
        requestQueue.add(zahtev);

    };

    public static void sacuvaj (String ime, String prezime, String korisIme, String lozinka){

            String url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/registracija";

            final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());

            JSONObject object = new JSONObject();
            try {
                object.accumulate("ime", ime);
                object.accumulate("prezime", prezime);
                object.accumulate("korisIme", korisIme);
                object.accumulate("lozinka", lozinka);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest zahtev = new JsonObjectRequest(Request.Method.POST, url, object ,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.e("ODGOVOR REGISTRACIJE", response.toString());

                    try{

                        if (response.getBoolean("uspesna registracija") == true)  {
                            brojClana = response.getInt("idClana");

                            delegat.posaljiTacnost(true);
                        }
                        if (response.getBoolean("uspesna registracija") == false) {
                            delegat.posaljiTacnost(false);
                        }

                    }catch (JSONException e){
                        Log.e("GRESKA PRI REGISTR.",e.getLocalizedMessage());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY ERROR GRESKAAAA", error.toString());
                }
            });
            requestQueue.add(zahtev);


    }

    public static void skidanjeFilmovaSaServera() {

        //ovde hocu da skinem listu filmova sa servera i ubacim u listu filmoviSaServera

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());

        String url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/filmovi";

        filmoviSaServera = new ArrayList<Film>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try{
                        JSONArray niz = response.getJSONArray("filmovi");

                        for(int i = 0; i < niz.length(); i++){

                            JSONObject obj = niz.getJSONObject(i);

                            int id = obj.getInt("id");                    // ovde radim parsiranje JSON-a
                            int godina = obj.getInt("godina");
                            String kat = obj.getString("kategorija");
                            String naziv = obj.getString("nazivFilma");
                            String zanr = obj.getString("zanr");


                            Film film = new Film(naziv,kat,zanr,godina,id);

                            filmoviSaServera.add(film);
                        }

                        filmovi.download(filmoviSaServera);              // ovde emituje skinute filmove


                    }catch (JSONException e){
                        Log.e("KLJUC", "GRESKA: " + e.getLocalizedMessage());
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Greska load filmova", error.getLocalizedMessage());
                }
        });

        requestQueue.add(jsonObjectRequest);


    }



    private static Film film;

    public static  void getNajpopFilm(){

        String url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/najpopularniji_film";

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());


        JsonObjectRequest zahtev = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    String naziv = response.getString("najpopularniji film");

                    JSONObject obj = response.getJSONObject("detalji");

                    String zanr = obj.getString("zanr");
                    int godina = obj.getInt("godina");
                    String kat = obj.getString("kategorija");
                    int id = obj.getInt("id");


                    film = new Film(naziv,kat,zanr,godina,id);

                    Log.e("NAJPOP FILM", film.toString());

                    naj.najpop(film);



                }catch (JSONException e){
                    Log.e("KLJUC", "GRESKA: " + e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getLocalizedMessage();
            }
        });

        requestQueue.add(zahtev);


    }


    public static void prethodniFilmovi() {

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());

        String url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/tvoja_prethodna_iznajmljivanja/" + String.valueOf(brojClana);

        filmoviSaServera = new ArrayList<Film>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray niz = response.getJSONArray("tvoja prethodna iznajmljivanja");

                    for(int i = 0; i < niz.length(); i++){

                        JSONObject obj = niz.getJSONObject(i);

                        int id = obj.getInt("id");
                        int godina = obj.getInt("godina");
                        String kat = obj.getString("kategorija");
                        String naziv = obj.getString("nazivFilma");
                        String zanr = obj.getString("zanr");


                        Film film = new Film(naziv,kat,zanr,godina,id);

                        filmoviSaServera.add(film);
                    }

                    filmovi.download(filmoviSaServera);              // ovde emituje skinute filmove


                }catch (JSONException e){
                    Log.e("KLJUC", "GRESKA: " + e.getLocalizedMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Greska load filmova", error.getLocalizedMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);


    }



    static  boolean rezultat;

    public static void rezervisi(String naziv) {

        String url = "http://192.168.1.6:8080/projekatMreze/webapi/videoklub/clan/" + String.valueOf(brojClana) + "/rezervacija/%22" + naziv + "%22";

        Log.e("film koji zeli ", naziv);

        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.getMainActivity().getApplicationContext());


        JsonObjectRequest zahtev = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    boolean tacno = response.getBoolean("rezervacija");

                    if (tacno) {
                        rezultat = true;
                        delegat.posaljiTacnost(true);
                    }
                    else {
                        rezultat = false;
                        delegat.posaljiTacnost(false);
                    }

                }catch (JSONException e){
                    Log.e("KLJUC", "GRESKA: " + e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getLocalizedMessage();
            }
        });

        requestQueue.add(zahtev);

    }
}




//162.168.1.6 kucna ip




