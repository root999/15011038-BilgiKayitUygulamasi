package com.example.mobildev_v03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class dersEkle extends AppCompatActivity {
    private ArrayList<Ogrenci> ogrenciList;
    private Ogrenci ogrenci;
    private ArrayList<Dersler> dersler;

    boolean cont=true;
    private EditText dersAdi;
    private EditText ogrNot;
    private Button ders_ekle;
    private  Button ana_sayfa;
    private int ogrenci_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_ekle);
        dersAdi=(EditText)findViewById(R.id.dersadi);
        ogrNot=(EditText)findViewById(R.id.not);
        ders_ekle=(Button)findViewById(R.id.dersEkle);
        ana_sayfa=(Button)findViewById(R.id.anaSayfa);
        dersler = new ArrayList<Dersler>();
        ogrenciList =new ArrayList<Ogrenci>();
        FileInputStream fis = null;
        try{
            fis = openFileInput("sondanonce");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(cont){
                ogrenci= (Ogrenci) ois.readObject();
                if(ogrenci != null)
                    ogrenciList.add(ogrenci);
                else
                    cont = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        for(Ogrenci ogr :ogrenciList){
            if(ogr.getKullaniciAdi().equals(getIntent().getStringExtra("kullaniciAdi"))){
                ogrenci=ogr;
                ogrenci_index=ogrenciList.indexOf(ogr);
            }
        }
        ders_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dersler ders = new Dersler();
                ders.setDersAdi(dersAdi.getText().toString().trim());
                ders.setOgrNot(Integer.parseInt(ogrNot.getText().toString().trim()));
                dersler.add(ders);
                dersAdi.setText("");
                ogrNot.setText("");
            }
        });
        ana_sayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ogrenci.setDersler(dersler);
                ogrenciList.set(ogrenci_index,ogrenci);
                Intent intent = new Intent(dersEkle.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
