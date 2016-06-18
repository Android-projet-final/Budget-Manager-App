package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by layely on 6/18/16.
 */
public class UtilitaireDOA {
    private SQLiteDatabase db;

    public UtilitaireDOA(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }


}
