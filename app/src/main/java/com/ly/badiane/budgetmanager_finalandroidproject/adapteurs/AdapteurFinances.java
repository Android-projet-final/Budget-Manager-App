package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ly.badiane.budgetmanager_finalandroidproject.finances.Finances;

import java.util.List;

/**
 * Created by badiane on 12/06/2016.
 */
public class AdapteurFinances extends ArrayAdapter< Finances> {

    public AdapteurFinances(Context context, List<Finances> personnes) {
        super(context, 0,personnes);
    }

    @Override
    public View getView(int position, View recup, ViewGroup parent) {
// créer ou récupérer un FinancesView
        FinancesView vueItem = (FinancesView) recup;
        if (vueItem == null)
            vueItem = FinancesView.create(parent); // <==(!!)
// afficher les valeurs
        vueItem.display(super.getItem(position));
        return vueItem;
    }

}
