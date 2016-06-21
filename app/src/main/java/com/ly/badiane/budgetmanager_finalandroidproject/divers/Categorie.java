package com.ly.badiane.budgetmanager_finalandroidproject.divers;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.ArrayList;

/**
 * Created by badiane on 13/06/2016.
 */
public class Categorie {
    public static final ArrayList<Categorie> ALL_EXP;
    public static final ArrayList<Categorie> ALL_BUD;
    public static final ArrayList<Categorie> ALL;

    static {
        ALL_EXP = new ArrayList<>();
        ALL_EXP.add(new Categorie(0, R.string.shoping, R.drawable.ic_add_shopping_cart_black_24dp));
        ALL_EXP.add(new Categorie(1, R.string.voyage, R.drawable.ic_flight_black_24dp));
        ALL_EXP.add(new Categorie(2, R.string.carmotor, R.drawable.ic_local_gas_station_black_24dp));
        ALL_EXP.add(new Categorie(3, R.string.food, R.drawable.ic_restaurant_black_24dp));
        ALL_EXP.add(new Categorie(4, R.string.eduction, R.drawable.education_black)); //TODO
        ALL_EXP.add(new Categorie(5, R.string.transport, R.drawable.transport_black)); //TODO
        ALL_EXP.add(new Categorie(6, R.string.other, R.drawable.other));
    }

    static {
        ALL_BUD = new ArrayList<>();
        ALL_BUD.add(new Categorie(7, R.string.salary, R.drawable.salary));
        ALL_BUD.add(new Categorie(8, R.string.gift, R.drawable.gift));
        ALL_BUD.add(new Categorie(9, R.string.selling, R.drawable.selling));
        ALL_BUD.add(new Categorie(10, R.string.award, R.drawable.award));
        ALL_BUD.add(new Categorie(6, R.string.other, R.drawable.other));
    }

    static {
        ALL = ALL_EXP;
        ALL.addAll(ALL_BUD);
    }

    private int id;
    private int label;
    private int icon;

    public Categorie(int id, int label, int icon) {
        this.id = id;
        this.label = label;
        this.icon = icon;
    }

    // public int
    public static Categorie getInstance(int i) {
        if (i <= (ALL.size() - 1) && i >= 0) {
            return ALL.get(i);
        }
        return null;
    }



    public int getId() {
        return this.id;
    }

    public int getStringResId() {
        return this.label;
    }

    public int getDrawableResId() {
        return icon;
    }
}
