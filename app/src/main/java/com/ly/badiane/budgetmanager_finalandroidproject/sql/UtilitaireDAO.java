package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by layely on 6/18/16.
 */
public final class UtilitaireDAO {
    private SQLiteDatabase db;

    public UtilitaireDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }

    public boolean mettreAjourNbLancementApp() {
        int newValue = nombreLancementApp() + 1;
        return mettreAjourUneColone(SqlHelper.NB_LANCEMENT_APP, String.valueOf(newValue));
    }

    public int nombreLancementApp() {
        String requete = "SELECT " + SqlHelper.COLUMN_UTILITAIRE_ID + ", " + SqlHelper.COLUMN_UTILITAIRE_VALUE +
                " FROM " + SqlHelper.TABLE_UTILITAIRE +
                " WHERE " + SqlHelper.COLUMN_UTILITAIRE_ID + "='" + SqlHelper.NB_LANCEMENT_APP + "'";

        Cursor cursor = db.rawQuery(requete, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            return cursor.getInt(cursor.getColumnIndex(SqlHelper.COLUMN_UTILITAIRE_VALUE));
        }
        cursor.close();
        return -1;
    }

    public boolean mettreAjourUneColone(String key, String newValue) {
        ContentValues cv = new ContentValues();
        cv.put(SqlHelper.COLUMN_UTILITAIRE_VALUE, newValue);
        int resultatDeLaRequete = db.update(SqlHelper.TABLE_UTILITAIRE, cv, SqlHelper.COLUMN_UTILITAIRE_ID + "='" + key + "'", null);
        if (resultatDeLaRequete > 0)
            return true;
        return false;
    }
}
