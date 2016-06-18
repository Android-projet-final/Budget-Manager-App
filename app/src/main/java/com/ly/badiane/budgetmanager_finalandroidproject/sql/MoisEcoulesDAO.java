package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by layely on 6/18/16.
 */
public class MoisEcoulesDAO {
    private SQLiteDatabase db;

    public MoisEcoulesDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }


    public boolean ajouterMois(Mois mois) {
        return ajouterMois(mois.getNumero(), mois.getAnnee());
    }

    public boolean ajouterMois(int mois, int annee) {
        ContentValues cv = new ContentValues();
        cv.put(SqlHelper.COLUMN_MOIS_ECOULES_MOIS, mois + "/" + annee);
        long resultatRequete = db.insert(SqlHelper.TABLE_MOIS_ECOULES, null, cv);
        if (resultatRequete == -1)
            return false;
        return true;
    }

    public boolean insertionLorsDuPremierLancement() {
        GregorianCalendar calendar = new GregorianCalendar();
        int moisCourant = calendar.get(GregorianCalendar.MONTH) + 1; //Janvier = 0 dans la classe GregorianCalandar d'ou l'incrementaion de 1
        int anneeCourant = calendar.get(GregorianCalendar.YEAR);

        ajouterMois(moisCourant, anneeCourant);
        return true;
    }

    public ArrayList<Mois> liste() {
        ArrayList<Mois> liste = new ArrayList<>();
        String requete = "SELECT " + SqlHelper.COLUMN_MOIS_ECOULES_ID + "," + SqlHelper.COLUMN_MOIS_ECOULES_MOIS +
                " FROM " + SqlHelper.TABLE_MOIS_ECOULES;

        Cursor cursor = db.rawQuery(requete, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            int m = cursor.getInt(cursor.getColumnIndex(SqlHelper.COLUMN_MOIS_ECOULES_ID));
            String mm_aaaa = cursor.getString(cursor.getColumnIndex(SqlHelper.COLUMN_MOIS_ECOULES_MOIS));
            liste.add(new Mois(mm_aaaa));
            cursor.moveToNext();
        }
        cursor.close();
        return liste;
    }
}
