package com.example.teodora.videoklub.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Teodora on 6/15/18.
 */

public class Captcha {

    Bitmap slika;
    String odgovor = "";
    int sirina, visina;
    int x = 0;
    int y = 0;
    int duzinaReci;
    static  ArrayList<Integer> boje;
    char karakter;

    public enum OpcijeTeksta{
        UPPERCASE_ONLY,
        LOWERCASE_ONLY
    }
    protected OpcijeTeksta opcijeTeksta;


    public static int color(){

        Random r = new Random();
        int broj;
        do {
            broj = r.nextInt(9);
        }while (boje.contains(broj));

        boje.add(broj);

        switch (broj){
            case 0: return Color.BLACK;
            case 1: return Color.BLUE;
            case 2: return Color.CYAN;
            case 3: return Color.DKGRAY;
            case 4: return Color.GRAY;
            case 5: return Color.GREEN;
            case 6: return Color.MAGENTA;
            case 7: return Color.RED;
            case 8: return Color.YELLOW;
            case 9: return Color.WHITE;
            default: return Color.WHITE;
        }
    }

    public Bitmap getSlika() {
        return slika;
    }

    public int getSirina() {
        return sirina;
    }

    public int getVisina() {
        return visina;
    }

    public void setSirina(int sirina) {
        this.sirina = sirina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public boolean proveriOdgovor(String odgovorKorisnika){
        return odgovorKorisnika.equals(odgovor);
    }


    public Captcha (int sirina, int visina, int duzinaReci, OpcijeTeksta opcije){

        setSirina(sirina);
        setVisina(visina);
        this.duzinaReci = duzinaReci;
        opcijeTeksta = opcije;

        boje = new ArrayList<>();
        slika = image();

    }

    protected Bitmap image(){

        LinearGradient gradient = new LinearGradient(0, 0, getSirina(), getVisina(), color(), color(), Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setShader(gradient);

        Bitmap bitmap = Bitmap.createBitmap(getSirina(), getVisina(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, getSirina(), getVisina(), paint);

        Paint tekstSlika = new Paint();
        tekstSlika.setDither(true);
        tekstSlika.setTextSize(getSirina()/getVisina() * 20);       // ovo

        Random r = new Random(System.currentTimeMillis());

        CharArrayWriter karakteri =  new CharArrayWriter();

        for (int i = 0; i < duzinaReci; i++){

            char ch = ' ';

            switch (opcijeTeksta){
                case UPPERCASE_ONLY:
                    ch = (char)(r.nextInt(91-65)+(65));
                    break;
                case LOWERCASE_ONLY:
                    ch = (char)(r.nextInt(123-97)+(97));
                    break;
            }
            karakteri.append(ch);
            odgovor += ch;
        }

        char[] nizKaraktera = karakteri.toCharArray();
        for( int i = 0; i <nizKaraktera.length; i++){
            x += 35; //x += (30 - (3*duzinaReci)) + (Math.abs(r.nextInt()) % (65-(1.2*duzinaReci)));
            y = 50; //y = 50 + Math.abs(r.nextInt()) % 50;

            Canvas cc = new Canvas(bitmap);

            tekstSlika.setColor(color());

            cc.drawText(nizKaraktera, i, 1, x, y, tekstSlika);
        }

        return bitmap;

    }

}
