package com.example.mobildev_v03;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.spec.ECField;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Ogrenci> ogrenciListe;
    private Ogrenci ogrenci;
    private EditText kullaniciAdi;
    private EditText sifre;
    private Button  loginBt;
    private Button  new_userBt;
    private final int REQUEST_CODE = 1001;
    private boolean cont=true;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ogrenciListe=new ArrayList<Ogrenci>();
        kullaniciAdi = (EditText)findViewById(R.id.idGirisBox);
        sifre=(EditText) findViewById(R.id.sifreGirisBox);
        loginBt=(Button)findViewById(R.id.loginBT);
        new_userBt=(Button)findViewById(R.id.new_userBT);
        new_userBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, kayitOl.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                File file = context.getFileStreamPath("sondanonce");
                FileInputStream fis = null;
                if(file.exists()){
                    Toast.makeText(getApplicationContext(),"File  exist",Toast.LENGTH_LONG).show();
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
                            if(ogrenci != null){
                                ogrenciListe.add(ogrenci);
                            }
                            else
                                cont = false;
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    for(Ogrenci ogr:ogrenciListe){
                        if(ogr.getKullaniciAdi().equals(kullaniciAdi.getText().toString().trim())){
                            if(ogr.getSifre().equals(sifre.getText().toString().trim())){
                                Intent intent = new Intent(MainActivity.this,ScrollActivity.class);
                                intent.putExtra("kullanici_adi",kullaniciAdi.getText().toString().trim());
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Şifre Yanlış",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Kayıtlı kişi yok, lütfen kayıt olun",Toast.LENGTH_LONG).show();
                  /*  try {
                        FileOutputStream fos = openFileOutput("bir", Context.MODE_PRIVATE);
                        String mesaj = "test";
                        fos.write(mesaj.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/
                }
            }
        });
    }
}
