package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categories;

import java.util.List;

/**
 * Created by badiane on 13/06/2016.
 */
public class AdapteurCategorie extends ArrayAdapter <Categories> {
    public AdapteurCategorie(Context context, List<Categories> categories) {
        super(context,0, categories);

    }


    @Override
    public View getView(int position, View recup, ViewGroup parent) {
// créer ou récupérer un FinancesView
        CategoriesView vueItem = (CategoriesView) recup;
        if (vueItem == null)
            vueItem = CategoriesView.create(parent); // <==(!!)
// afficher les valeurs
        vueItem.display(super.getItem(position));
        return vueItem;
    }
}