package com.ly.badiane.budgetmanager_finalandroidproject.finances;

import java.util.Date;

/**
 * Created by badiane on 12/06/2016.
 */
/*
Cette class est une prototypages de n'importes quelles entrée ou sortie d'argents:
Donc c'est classe que devra etendre Depenses et Dépot que nous appelons ici Budget
 */
public abstract class Finance {
  //  private static int i = 0; //je pense on en aura pas besoin car on recupere dans la base de donnée
    private int id;
    private Double montant;
    private String note;
    private String categorie;
    private Date date;
    private String frequences;


    public Finance(int id,Double montant, String note, String categorie, String frequences, Date date) {
        this.id = id;
        this.montant = montant;
        this.note = note;
        this.categorie = categorie;
        this.frequences = frequences;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getFrequences() {
        return frequences;
    }

    public void setFrequences(String frequences) {
        this.frequences = frequences;
    }
}
