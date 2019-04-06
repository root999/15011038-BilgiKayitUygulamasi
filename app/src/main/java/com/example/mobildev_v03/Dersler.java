package com.example.mobildev_v03;

import android.content.Context;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.FileInputStream;

public class Dersler {

    private String dersAdi;
    private int ogrNot;

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public int getOgrNot() {
        return ogrNot;
    }

    public void setOgrNot(int ogrNot) {
        this.ogrNot = ogrNot;
    }
    public static ArrayList<Dersler> getData(Context context,String kullanici_adi) {
        ArrayList<Dersler> derslist = new ArrayList<Dersler>();
        boolean cont=true;
        int ogrenci_index;
        FileInputStream fis = null;
        Ogrenci ogrenci=null;
        ArrayList<Ogrenci> ogrenciList = new ArrayList<>();
        try{
           fis = context.openFileInput("sondanonce");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(cont){
                ogrenci= (Ogrenci) ois.readObject();
                if(ogrenci != null) {
                    ogrenciList.add(ogrenci);
                } else
                    cont = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        for(Ogrenci ogr :ogrenciList){
            if(ogr.getKullaniciAdi().equals(kullanici_adi)){
                ogrenci=ogr;
            }
        }
       for(Dersler ders:ogrenci.getDersler()){
           derslist.add(ders);
       }


        return derslist;


    }
}
