package com.ly.badiane.budgetmanager_finalandroidproject.divers;

/**
 * Created by layely on 6/18/16.
 * format du mois : MM/YYYY
 */
public class Mois {
    /*On commence à compter à partir de 1
        donc on a Janvier = 1, Fevrier = 2 ... Decembre = 12
    */
    private int numero;
    private int annee;

    public Mois(int numero, int annee) {
        this.numero = numero;
        this.annee = annee;
    }

    /*
    *
    *Crée un mois à partir d'un String de format MM/YYYY ou M/YYYY
    */
    public Mois(String mois) {
        String[] tab = mois.split("/");
        if (tab.length != 2) {
            //TODO throw Exception;
        }
        numero = Integer.parseInt(tab[0]);
        annee = Integer.parseInt(tab[1]);

    }

    @Override
    public String toString() {
        return numero + "/" + annee;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
