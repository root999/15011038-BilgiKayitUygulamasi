package com.example.mobildev_v03;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class ScrollActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        String kullanici_adi;
        recyclerView = (RecyclerView)findViewById(R.id.recylerViewID);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        kullanici_adi= getIntent().getStringExtra("kullanici_adi");
        mAdapter= new MyAdapter(this,Dersler.getData(getApplicationContext(),getIntent().getStringExtra("kullanici_adi").trim()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
