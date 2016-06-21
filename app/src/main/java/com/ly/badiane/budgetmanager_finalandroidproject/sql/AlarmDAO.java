package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;

import java.util.Calendar;

/**
 * Created by layely on 6/21/16.
 */
public class AlarmDAO {
    private SQLiteDatabase db;

    public AlarmDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }

    public boolean ajouter(int id, Calendar c) {
        ContentValues cv = new ContentValues();
        cv.put(SqlHelper.COLUMN_TRANSACTION_ID, id);
        cv.put(SqlHelper.COLUMN_ALARM_DATE_TIME, Utilitaire.calendarToCompleteString(c));
        if (db.insert(SqlHelper.TABLE_ALARM, null, cv) == -1)
            return false;
        return true;
    }

    public boolean supprimer(int id) {
        if (db.delete(SqlHelper.TABLE_ALARM, SqlHelper.COLUMN_TRANSACTION_ID + "=" + id, null) == 0)
            return false;
        return true;
    }
}
