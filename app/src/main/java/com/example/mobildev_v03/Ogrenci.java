package com.example.mobildev_v03;

import java.io.Serializable;
import java.util.ArrayList;

public class Ogrenci implements Serializable {
    private String kullaniciAdi;
    private String sifre;
    private String isim;
    private String soyisim;
    private String dogumyeri;
    private String dogumtarihi;
    private String tcNo;
    private String telefon;
    private ArrayList<Dersler> dersler;

    public Ogrenci(String kullaniciAdi,String sifre,String isim,String soyisim,String dogumyeri,String dogumtarihi,String tcNo,String telefon){
        this.kullaniciAdi=kullaniciAdi;
        this.sifre=sifre;
        this.isim=isim;
        this.soyisim=soyisim;
        this.dogumyeri=dogumyeri;
        this.dogumtarihi=dogumtarihi;
        this.tcNo=tcNo;
        this.telefon=telefon;
    }

    public ArrayList<Dersler> getDersler() {
        return dersler;
    }

    public String getDogumtarihi() {
        return dogumtarihi;
    }

    public String getDogumyeri() {
        return dogumyeri;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getTcNo() {
        return tcNo;
    }

    public String getTelefon() {
        return telefon;
    }
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setDersler(ArrayList<Dersler> dersler) {
        this.dersler = dersler;
    }


}
