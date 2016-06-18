package com.ly.badiane.budgetmanager_finalandroidproject.finances;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categories;

import java.util.GregorianCalendar;

/**
 * Created by badiane on 12/06/2016.
 */
/*
Cette classe est une prototypage de n'importe quelle entrée ou sortie d'argents:
 */
public class Transaction {
    //  private static int i = 0; //je pense on en aura pas besoin car on recupere dans la base de donnée

    //Definition des constantes
    private static final int ENTREE = 101;
    private static final int SORTIE = 102;

    private static final int ANNUEL = 201;
    private static final int MENSUEL = 202;
    private static final int HEBDOMADAIRE = 203;
    private static final int JOURNALIER = 204;
    private static final int UNE_FOIS = 205;

    private int id;
    private Double montant;
    private Categories categories;
    private String note;
    private GregorianCalendar date;
    private int frequence; //annuel, mensuel, hebdomadaire, journalier, non frequenciel
    private int type; //Entrée ou sortie

    public Transaction(int type, Double montant, Categories categories, String note, GregorianCalendar date, int frequence) {

        if (type != ENTREE && type != SORTIE) {
            //Exception de type TransactionTypeNotExist doit etre lancée
        }

        if (frequence != ANNUEL && frequence != MENSUEL && frequence != HEBDOMADAIRE && frequence != JOURNALIER && frequence != UNE_FOIS) {
            //Exception de type TransactionFrequenceNotExist
        }

        this.type = type;
        this.montant = montant;
        this.note = note;
        this.categories = categories;
        this.frequence = frequence;
        this.date = date;
    }

    public Transaction(int id, int type, Double montant, Categories categories, String note, GregorianCalendar date, int frequence) {
        this(type, montant, categories, note, date, frequence);
        this.id = id;
    }


    //Getters and Setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getFrequences() {
        return frequence;
    }

    public void setFrequences(String frequences) {
        this.frequence = frequence;
    }
}
