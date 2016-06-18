package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by layely on 6/18/16.
 */
public class MoisEcoulesDAO {
    private SQLiteDatabase db;

    public MoisEcoulesDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }


    public boolean ajouterMois(int mois, int annee) {
        ContentValues cv = new ContentValues();
        cv.put(SqlHelper.COLUMN_MOIS_ECOULES_MOIS, mois + "/" + annee);
        long resultatRequete = db.insert(SqlHelper.TABLE_MOIS_ECOULES, null, cv);
        if (resultatRequete == -1)
            return false;
        return true;
    }

    public boolean insertionLorsDuLancement1() {
        //TODO
        return true;
    }
}
