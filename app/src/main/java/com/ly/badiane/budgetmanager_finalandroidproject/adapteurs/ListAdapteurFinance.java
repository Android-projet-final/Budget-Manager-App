package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

import java.util.List;

/**
 * Created by badiane on 12/06/2016.
 */
public class ListAdapteurFinance extends ArrayAdapter<Transaction> {

    public ListAdapteurFinance(Context context, List<Transaction> transactions) {
        super(context, R.layout.view_finances, transactions);
    }

    @Override
    public View getView(int position, View recup, ViewGroup parent) {
//// créer ou récupérer un FinancesView
//        FinancesView vueItem = (FinancesView) recup;
//        if (vueItem == null)
//            vueItem = FinancesView.create(parent); // <==(!!)
//// afficher les valeurs
//        vueItem.display(super.getItem(position));
//        return vueItem;

        View row = recup;
        if (row == null) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            row = li.inflate(R.layout.view_finances, parent, false);
        }

        ImageView iconCategorie = (ImageView) row.findViewById(R.id.imageCategories);
        TextView textViewCategorie = (TextView) row.findViewById(R.id.txtcategories);
        TextView textViewDate = (TextView) row.findViewById(R.id.date);
        TextView textViewMontant = (TextView) row.findViewById(R.id.montant);

        Transaction transaction = getItem(position);

        iconCategorie.setImageResource(transaction.getCategorie().getDrawableResId());
        textViewCategorie.setText(transaction.getCategorie().getStringResId());
        textViewDate.setText(Utilitaire.calandarToString(transaction.getDate()));
        textViewMontant.setText(transaction.getMontant().toString());

        return row;
    }

}
