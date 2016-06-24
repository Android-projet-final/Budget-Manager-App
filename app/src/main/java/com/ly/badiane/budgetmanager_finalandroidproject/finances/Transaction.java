package com.ly.badiane.budgetmanager_finalandroidproject.finances;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categorie;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by badiane on 12/06/2016.
 */
/*
Cette classe est une prototypage de n'importe quelle entrée ou sortie d'argents:
 */
public class Transaction {
    //  private static int i = 0; //je pense on en aura pas besoin car on recupere dans la base de donnée

    //Definition des constantes
    public static final int ENTREE = 101;
    public static final int SORTIE = 102;

    public static final int UNE_FOIS = 200;
    public static final int JOURNALIER = 201;
    public static final int HEBDOMADAIRE = 202;
    public static final int MENSUEL = 203;

    private int id;
    private Double montant;
    private Categorie categorie;
    private String note;
    private GregorianCalendar date;
    private int frequence; //annuel, mensuel, hebdomadaire, journalier, non frequenciel
    private int type; //Entrée ou sortie

    public Transaction(int type, Double montant, Categorie categorie, String note, GregorianCalendar date, int frequence) {

        if (type != ENTREE && type != SORTIE) {
            //Exception de type TransactionTypeNotExist doit etre lancée
        }

        if (frequence != MENSUEL && frequence != HEBDOMADAIRE && frequence != JOURNALIER && frequence != UNE_FOIS) {
            //Exception de type TransactionFrequenceNotExist
        }

        this.type = type;
        this.montant = montant;
        this.note = note;
        this.categorie = categorie;
        this.frequence = frequence;
        this.date = date;
    }

    public Transaction(int id, int type, Double montant, Categorie categorie, String note, GregorianCalendar date, int frequence) {
        this(type, montant, categorie, note, date, frequence);
        this.id = id;
    }


    public static Double totalBudget(List<Transaction> list, Mois mois) {
        double sum = 0;
        for (Transaction t : list) {
            if (t.isBudget())
                sum += t.getTotalDuMois(mois);
        }
        return sum;
    }

    public static Double totalExpensese(List<Transaction> list, Mois mois) {
        double sum = 0;
        for (Transaction t : list) {
            if (t.isExpensise()) {
                sum += t.getTotalDuMois(mois);
            }
        }
        return sum;
    }

    private Double getTotalDuMois(Mois mois) {
        if (this.getFrequences() == Transaction.UNE_FOIS || this.getFrequences() == Transaction.MENSUEL)
            return this.getMontant();

        if(mois == null)
            return this.getMontant();

        GregorianCalendar gc = new GregorianCalendar();
        try {
           gc.setTimeInMillis( Utilitaire.stringToCalandar(mois.toString()).getTimeInMillis() );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        gc.setTimeInMillis(this.getDate().getTimeInMillis());

        gc.set(GregorianCalendar.MONTH, this.getDate().get(GregorianCalendar.MONTH));

//        if(gc == null)
//            return null;

        if (this.getFrequences() == Transaction.JOURNALIER) {
            int nbJour = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            return this.getMontant() * nbJour;
        }

        if (this.getFrequences() == Transaction.HEBDOMADAIRE) {
            int nbHeb = gc.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
            return this.getMontant() * nbHeb;
        }

        return null;
    }


    public boolean isBudget() {
        return (type == ENTREE ? true : false);
    }

    public boolean isExpensise() {
        return !isBudget();
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getFrequences() {
        return frequence;
    }

    public void setFrequences(int frequence) {
        this.frequence = frequence;
    }

    public int getFrequencesResID() {
        if (frequence == Transaction.UNE_FOIS)
            return R.string.once;
        if (frequence == Transaction.JOURNALIER)
            return R.string.every_day;
        if (frequence == Transaction.HEBDOMADAIRE)
            return R.string.every_week;
        if (frequence == Transaction.MENSUEL)
            return R.string.every_month;
        return 0;
    }
}
