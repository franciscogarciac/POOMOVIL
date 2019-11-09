package com.example.prueba1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPaises extends ArrayAdapter<Paises> {
    public AdaptadorPaises(Context context, ArrayList<Paises> datos) {
        super(context, R.layout.ly_item, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_item, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.txtPais);
        lblTitulo.setText(getItem(position).getPais());

        ImageView imageView = (ImageView) item.findViewById(R.id.imgpais);
        Glide.with(this.getContext()).load(getItem(position).getUrlpais()).into(imageView);
        return (item);
    }
}
