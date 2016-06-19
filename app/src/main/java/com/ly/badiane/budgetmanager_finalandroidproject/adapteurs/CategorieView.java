package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categorie;

/**
 * Created by badiane on 13/06/2016.
 */
public class CategorieView extends RelativeLayout {
    private ImageView img;
    private TextView txt;

    public CategorieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static CategorieView create(ViewGroup parent) {
        LayoutInflater li =
                LayoutInflater.from(parent.getContext());
        CategorieView itemView = (CategorieView)
                li.inflate(R.layout.item_categories, parent, false);
        itemView.findViews();
        return itemView;
    }

    private void findViews() {
        img = (ImageView) findViewById(R.id.imageCat);
        txt = (TextView) findViewById(R.id.txtcat);
    }

    public void display(final Categorie categorie) {
        img.setImageResource(categorie.getDrawableResId());
        txt.setText(getResources().getString(categorie.getStringResId()));
    }
}
