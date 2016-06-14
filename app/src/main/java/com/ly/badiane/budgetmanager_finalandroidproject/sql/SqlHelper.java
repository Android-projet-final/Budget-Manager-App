package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by badiane on 14/06/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    private final static int  VersionDataBase =  1;//Verion of database
    private final static String databaseName = "budgetmanager.db";//name of database
    //requete de creation de la table finances
    private String tableFinancesRequette = "CREATE TABLE finances ( mois NUMBER AUTOINCREMENT PRIMARY KEY," +
            "an NUMBER , montant NUMBER, categories TEXT, note TEXT," +
            "frequences TEXT)";
    private String updateDroper = "DROP TABLE IF EXISTS finances";

    public SqlHelper (Context context){
        super(context,databaseName,null,VersionDataBase);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableFinancesRequette);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(updateDroper);
        onCreate(db);
    }
}
