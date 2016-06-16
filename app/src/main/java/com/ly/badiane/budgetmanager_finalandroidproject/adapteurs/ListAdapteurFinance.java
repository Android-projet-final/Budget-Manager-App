package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

import java.util.List;

/**
 * Created by badiane on 12/06/2016.
 */
public class ListAdapteurFinance extends ArrayAdapter<Transaction> {

    public ListAdapteurFinance(Context context, List<Transaction> transactions) {
        super(context, 0, transactions);
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
