package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categorie;

import java.util.List;

/**
 * Created by badiane on 13/06/2016.
 */
public class AdapteurCategorie extends ArrayAdapter<Categorie> {


    public AdapteurCategorie(Context context, int resource, List<Categorie> objects) {
        super(context, resource, objects);
    }

    public AdapteurCategorie(Context context, int resource, int textViewResourceId, List<Categorie> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View recup, ViewGroup parent) {

        Categorie categorie = getItem(position);

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_categories, parent, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageCat);
        TextView textView = (TextView) itemView.findViewById(R.id.txtcat);
        imageView.setImageResource(categorie.getDrawableResId());
        textView.setText(categorie.getStringResId());
        return itemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Categorie categorie = getItem(position);

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_categories, parent, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageCat);
        TextView textView = (TextView) itemView.findViewById(R.id.txtcat);
        imageView.setImageResource(categorie.getDrawableResId());
        textView.setText(categorie.getStringResId());
        return itemView;
    }
}
