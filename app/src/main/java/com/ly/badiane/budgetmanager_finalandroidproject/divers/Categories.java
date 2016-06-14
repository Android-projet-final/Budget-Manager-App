package com.ly.badiane.budgetmanager_finalandroidproject.divers;

/**
 * Created by badiane on 13/06/2016.
 */
public class Categories  {
    private String nomCategorie;
    private int img;
    public Categories(String nomCategorie, int img){
        this.nomCategorie = nomCategorie;
        this.img = img;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
