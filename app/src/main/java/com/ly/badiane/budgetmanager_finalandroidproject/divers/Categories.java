package com.ly.badiane.budgetmanager_finalandroidproject.divers;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.ArrayList;

/**
 * Created by badiane on 13/06/2016.
 */
public class Categories  {
    static  ArrayList<Categories> ALL;

    static {
        ALL = new ArrayList<>();
        ALL.add(new Categories(0, R.string.shoping,R.drawable.ic_add_shopping_cart_black_24dp));
        ALL.add(new Categories(1, R.string.voyage,R.drawable.ic_flight_black_24dp));
        ALL.add(new Categories(2, R.string.carmotor,R.drawable.ic_sentiment_neutral_black_24dp)); // TODO
        ALL.add(new Categories(3, R.string.divers,R.drawable.ic_local_gas_station_black_24dp));
        ALL.add(new Categories(4, R.string.food,R.drawable.ic_restaurant_black_24dp));
        ALL.add(new Categories(5, R.string.eduction,R.drawable.ic_sentiment_neutral_black_24dp));//TODO
    }

    int id;
    private int nomCategorie;
    private int img;
    public Categories(int id, int nomCategorie, int img){
        this.nomCategorie = nomCategorie;
        this.img = img;
    }

   // public int
    public static Categories getInstance(int i){
        if(i<=5 && i>=0)
                  return ALL.get(i);
                          return null;
    }

    public int getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(int nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getId(){
        return  id;
    }

    public int getStringResId() {
        return this.nomCategorie;
    }

    public int getDrawableResId() {
        return img;
    }
}
