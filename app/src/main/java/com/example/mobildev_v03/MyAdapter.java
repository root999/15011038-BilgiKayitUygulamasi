package com.example.mobildev_v03;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by tchzafer on 21/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Dersler> dersList;
    LayoutInflater inflater;
    String kullanici_adi;
    public MyAdapter(Context context, ArrayList<Dersler> dersList) {
        inflater = LayoutInflater.from(context);
        this.dersList = dersList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.example, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dersler selectedProduct = dersList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return dersList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dersAdi, dersNot;
        ImageView productImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            dersAdi = (TextView) itemView.findViewById(R.id.dersAdiID);
            dersNot = (TextView) itemView.findViewById(R.id.notID);
            productImage = (ImageView) itemView.findViewById(R.id.image);
            deleteproduct = (ImageView) itemView.findViewById(R.id.infoID);
            deleteproduct.setOnClickListener(this);
        }

        public void setData(Dersler selectedProduct, int position) {

            this.dersAdi.setText(selectedProduct.getDersAdi());
            this.dersNot.setText(selectedProduct.getOgrNot());
        }


        @Override
        public void onClick(View v) {


        }


    }
}