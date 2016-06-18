package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

/**
 * Created by badiane on 12/06/2016.
 */
public class FinancesView extends RelativeLayout {
    private TextView montant;
    private ImageView categorieImage;
    private TextView date;
    private TextView categoriesTxt;

    //private
    public FinancesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static FinancesView create(ViewGroup parent) {
        LayoutInflater li =
                LayoutInflater.from(parent.getContext());
        FinancesView itemView = (FinancesView)
                li.inflate(R.layout.item, parent, false);
        itemView.findViews();
        return itemView;
    }
    private void findViews(){
    montant = (TextView)findViewById(R.id.montant);
        date = (TextView)findViewById(R.id.date);
        categoriesTxt = (TextView)findViewById(R.id.txtcategories);
        categorieImage = (ImageView)findViewById(R.id.imageCategories);
    }

    public void display(final Transaction transaction) {
        categorieImage.setImageResource(transaction.getCategories().getDrawableResId());
        categoriesTxt.setText(getResources().getString(transaction.getCategories().getStringResId()));
        date.setText(transaction.getDate().toString());
        montant.setText(transaction.getMontant().toString());

    }
}
