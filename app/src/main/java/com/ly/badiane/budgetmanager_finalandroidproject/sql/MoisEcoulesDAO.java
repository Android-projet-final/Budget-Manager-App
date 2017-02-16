package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;
import com.ly.badiane.budgetmanager_finalandroidproject.services_receivers.AlarmTriggerService;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by layely on 6/18/16.
 */
public class MoisEcoulesDAO {
    Context context = null;
    private SQLiteDatabase db;

    public MoisEcoulesDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
        this.context = context;
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
        Intent serviceIntent = new Intent(context, AlarmTriggerService.class);
        context.stopService(serviceIntent);

        GregorianCalendar calendar = new GregorianCalendar();
        int moisCourant = calendar.get(GregorianCalendar.MONTH) + 1; //Janvier = 0 dans la classe GregorianCalandar d'ou l'incrementaion de 1
        int anneeCourant = calendar.get(GregorianCalendar.YEAR);

//        ajouterMois(moisCourant, anneeCourant)
        for (int j = 2000; j < anneeCourant; j++)
            for (int i = 1; i <= 12; i++)
                ajouterMois(i, j);
        for (int i = 1; i <= moisCourant; i++) {
            ajouterMois(i, anneeCourant);
        }

        context.startService(serviceIntent);
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

    public void reInit() {
        clear();
        insertionLorsDuPremierLancement();
    }

    private void clear() {
        db.delete(SqlHelper.TABLE_MOIS_ECOULES, null, null);
    }
}
