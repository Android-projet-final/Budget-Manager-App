package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by badiane on 14/06/2016.
 */
public class RequetteFinances {
    private static String selection  = "SELECT * FROM finances";
    private SQLiteDatabase db;
    public RequetteFinances(Context context){
        db = new  SqlHelper (context).getWritableDatabase();
    }
    public  Cursor selectFinances(){
        Cursor cursor = db.rawQuery(selection,null);
        return cursor;
    }
    public long inserFinances(){
        ContentValues values = new ContentValues();
        return db.insert("finances",null,values);
    }
    public boolean delecteFinances(int id){

        return true;
    }
}
