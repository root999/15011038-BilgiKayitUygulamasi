package com.example.mobildev_v03;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class kayitOl extends AppCompatActivity {
    private EditText kullaniciTxt;
    private EditText sifreTxt;
    private EditText nameTxt;
    private EditText surnameTxt;
    private EditText birtplaceTxt;
    private EditText birthdayTxt;
    private EditText SSNTxt;
    private EditText phoneNumTxt;
    private Button gonderBtn;
    private Button pickPhotoBtn;
    private final int REQUEST_CODE = 1001;
    private final int GALLERY = 1002;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        kullaniciTxt = (EditText) findViewById(R.id.kullaniciAdiGir);
        sifreTxt = (EditText) findViewById(R.id.sifreGir);
        nameTxt = (EditText) findViewById(R.id.name);
        surnameTxt = (EditText) findViewById(R.id.surname);
        birtplaceTxt = (EditText) findViewById(R.id.BirthPlace);
        birthdayTxt = (EditText) findViewById(R.id.birthDate);
        SSNTxt = (EditText) findViewById(R.id.Kimlik_No);
        gonderBtn = (Button) findViewById(R.id.sendButton);
        pickPhotoBtn = (Button) findViewById(R.id.pickPhoto);
        phoneNumTxt = (EditText) findViewById(R.id.phoneNum);


        gonderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(kayitOl.this, secondActivity.class);
                intent.putExtra("kullaniciAdi",kullaniciTxt.getText().toString().trim());
                intent.putExtra("sifre", sifreTxt.getText().toString().trim());
                intent.putExtra("nameID", nameTxt.getText().toString().trim());
                intent.putExtra("surnameID", surnameTxt.getText().toString().trim());
                intent.putExtra("birthplaceID", birtplaceTxt.getText().toString().trim());
                intent.putExtra("birthdayID", birthdayTxt.getText().toString().trim());
                intent.putExtra("ssnID", SSNTxt.getText().toString().trim());
                intent.putExtra("phoneNumID", phoneNumTxt.getText().toString().trim());
                Ogrenci ogrenci = new Ogrenci(kullaniciTxt.getText().toString(), sifreTxt.getText().toString(),nameTxt.getText().toString(),surnameTxt.getText().toString(), birtplaceTxt.getText().toString(),birthdayTxt.getText().toString(),SSNTxt.getText().toString(),phoneNumTxt.getText().toString());
                try {
                    FileOutputStream fos = openFileOutput("sondanonce", MODE_APPEND);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(ogrenci);
                    oos.close();
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        pickPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent galleryIntent) {
        super.onActivityResult(requestCode, resultCode, galleryIntent);
        if (requestCode == GALLERY && resultCode == RESULT_OK && null != galleryIntent) {
            Uri selectedImage = galleryIntent.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }
}
