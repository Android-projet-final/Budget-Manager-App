package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public String getDateTime(int id) {
        String requete = "SELECT " + SqlHelper.COLUMN_ALARM_DATE_TIME + " FROM " + SqlHelper.TABLE_ALARM + " WHERE " + SqlHelper.COLUMN_TRANSACTION_ID + "=" + id;
        Cursor cursor = db.rawQuery(requete, null);

        String strDateTime = null;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            strDateTime = cursor.getString(cursor.getColumnIndex(SqlHelper.COLUMN_ALARM_DATE_TIME));
            cursor.moveToNext();
        }
        cursor.close();

        return strDateTime;
    }

    public List<Integer> idAlarmList(Calendar c) {
        String requete = "SELECT " + SqlHelper.COLUMN_TRANSACTION_ID +
                " FROM " + SqlHelper.TABLE_ALARM +
                " WHERE " + SqlHelper.COLUMN_ALARM_DATE_TIME + "='" + Utilitaire.calendarToCompleteString(c) + "'";
        Cursor cursor = db.rawQuery(requete, null);

        ArrayList<Integer> list = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getInt(cursor.getColumnIndex(SqlHelper.COLUMN_TRANSACTION_ID)));
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }
}
