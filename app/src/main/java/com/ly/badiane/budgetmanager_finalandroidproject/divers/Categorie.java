package com.ly.badiane.budgetmanager_finalandroidproject.divers;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.ArrayList;

/**
 * Created by badiane on 13/06/2016.
 */
public class Categorie {
    public static final ArrayList<Categorie> ALL;

    static {
        ALL = new ArrayList<>();
        ALL.add(new Categorie(0, R.string.shoping, R.drawable.ic_add_shopping_cart_black_24dp));
        ALL.add(new Categorie(1, R.string.voyage, R.drawable.ic_flight_black_24dp));
        ALL.add(new Categorie(2, R.string.carmotor, R.drawable.ic_local_gas_station_black_24dp));
        ALL.add(new Categorie(3, R.string.food, R.drawable.ic_restaurant_black_24dp));
        ALL.add(new Categorie(4, R.string.eduction, R.drawable.education_black)); //TODO
        ALL.add(new Categorie(5, R.string.transport, R.drawable.transport_black)); //TODO
        ALL.add(new Categorie(6, R.string.divers, R.drawable.divers));
    }

    private int id;
    private int label;
    private int icon;

    public Categorie(int id, int label, int icon) {
        this.label = label;
        this.icon = icon;
    }

    // public int
    public static Categorie getInstance(int i) {
        if (i <= (ALL.size() - 1) && i >= 0)
            return ALL.get(i);
        return null;
    }


    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public int getStringResId() {
        return this.label;
    }

    public int getDrawableResId() {
        return icon;
    }
}
