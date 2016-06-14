package com.ly.badiane.budgetmanager_finalandroidproject.adapteurs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categories;

/**
 * Created by badiane on 13/06/2016.
 */
public class CategoriesView extends RelativeLayout {
    public CategoriesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public static CategoriesView create(ViewGroup parent) {
        LayoutInflater li =
                LayoutInflater.from(parent.getContext());
        CategoriesView itemView = (CategoriesView)
                li.inflate(R.layout.item_categories, parent, false);
        itemView.findViews();
        return itemView;
    }
    private void findViews(){

    }
    public void display(final Categories categories) {

    }
}
