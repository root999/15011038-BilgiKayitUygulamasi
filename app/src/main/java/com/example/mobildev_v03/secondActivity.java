package com.example.mobildev_v03;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class secondActivity extends AppCompatActivity {
    private TextView nameTxt;
    private TextView surnameTxt;
    private TextView birtplaceTxt;
    private TextView birthdayTxt;
    private TextView SSNTxt;
    private TextView phoneTXT;
    private Button araBtn;
    private Button  dersEkle;
    private String kullanici_adi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameTxt=(TextView)findViewById(R.id.nameShow);
        surnameTxt=(TextView) findViewById(R.id.surnameShow);
        birtplaceTxt=(TextView) findViewById(R.id.birthPlaceShow);
        birthdayTxt=(TextView) findViewById(R.id.birthdateShow);
        SSNTxt=(TextView) findViewById(R.id.SSNshow);
        phoneTXT=(TextView) findViewById(R.id.phoneTxt);
        araBtn=(Button)findViewById(R.id.call);
        dersEkle=(Button)findViewById(R.id.DersEkleBt);
        araBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+9"+phoneTXT.getText().toString().trim()));
                startActivity(intent);
            }
        });
        dersEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, dersEkle.class);
                intent.putExtra("kullaniciAdi",kullanici_adi);
                startActivity(intent);
            }
        });
        try{
            kullanici_adi=getIntent().getStringExtra("kullaniciAdi");
            String data=getIntent().getStringExtra("nameID");
            nameTxt.setText(data);
            data=getIntent().getStringExtra("surnameID");
            surnameTxt.setText(data);
            data=getIntent().getStringExtra("birthplaceID");
            birtplaceTxt.setText(data);
            data=getIntent().getStringExtra("birthdayID");
            birthdayTxt.setText(data);
            data=getIntent().getStringExtra("ssnID");
            SSNTxt.setText(data);
            data=getIntent().getStringExtra("phoneNumID");
            phoneTXT.setText(data);
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
