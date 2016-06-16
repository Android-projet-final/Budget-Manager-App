package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by badiane on 14/06/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    private final static int VersionDataBase = 2;//Verion of database
    private final static String databaseName = "budgetmanager.db";//name of database

    //requete de creation de la table Transaction

    private String requeteCreationTableTransaction = "CREATE TABLE Transaction (" +
            "trans_id INTEGER AUTOINCREMENT PRIMARY KEY," +
            "type INTEGER, " +
            "montant REAL, " +
            "categories TEXT, " +
            "note TEXT," +
            "date TEXT, " +
            "frequences INTEGER" +
            ")";

    private String requeteDeMisAjourTableTransaction = "DROP TABLE IF EXISTS Transaction";

    public SqlHelper(Context context) {
        super(context, databaseName, null, VersionDataBase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(requeteCreationTableTransaction);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(requeteDeMisAjourTableTransaction);
        onCreate(db);
    }
}
