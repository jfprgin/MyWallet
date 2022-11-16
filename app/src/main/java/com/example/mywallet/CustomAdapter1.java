package com.example.mywallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList  kategorija, vrijednost, biljeska, id_troska;

    CustomAdapter1( Context context, ArrayList kategorija, ArrayList vrijednost, ArrayList biljeska, ArrayList id_troska){
        this.context=context;
        this.id_troska=id_troska;
        this.kategorija=kategorija;
        this.vrijednost=vrijednost;
        this.biljeska=biljeska;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_kategorija.setText(String.valueOf(kategorija.get(position)));
        holder.tv_vrijednost.setText(String.valueOf(vrijednost.get(position)));
        holder.tv_biljeska.setText(String.valueOf(biljeska.get(position)));
        holder.tv_id_troska.setText(String.valueOf(id_troska.get(position)));
        holder.row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTrosak.class);
                intent.putExtra("kategorija", String.valueOf(kategorija.get(position)));
                intent.putExtra("vrijednost", String.valueOf(vrijednost.get(position)));
                intent.putExtra("biljeska", String.valueOf(biljeska.get(position)));
                intent.putExtra("id", String.valueOf(id_troska.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategorija.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_kategorija, tv_vrijednost, tv_biljeska, tv_id_troska;
        LinearLayout row_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_troska=itemView.findViewById(R.id.tv_id);
            tv_kategorija=itemView.findViewById(R.id.tv_kategorija);
            tv_vrijednost=itemView.findViewById(R.id.tv_vrijednost);
            tv_biljeska=itemView.findViewById(R.id.tv_biljeska);
            row_layout=itemView.findViewById(R.id.row_layout);

        }
    }
}
