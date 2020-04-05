package com.example.project_spread_sheet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<String> name, phone_no;

    public Adapter (Context Context, List<String> name, List<String> phone_no){
        this.inflater = LayoutInflater.from(Context);
        this.name = name;
        this.phone_no= phone_no;

        Log.d("TAG","Adapter: "+name);


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nm = name.get(position);
        String phno= phone_no.get(position);

        holder.names.setText(nm);
        holder.content_phno.setText(phno);

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView names,content_phno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.name_text_view);
            content_phno = itemView.findViewById(R.id.phone_no_text_view);
        }
    }
}
