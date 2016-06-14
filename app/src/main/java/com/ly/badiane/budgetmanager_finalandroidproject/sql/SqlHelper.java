package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by badiane on 14/06/2016.
 */
public class SqlHelper extends SQLiteDatabase{

    //requete de creation de la table finances
    private String tableFinances = "mois NUMBER AUTOINCREMENT PRIMARY KEY," +
            "an NUMBER , montant NUMBER, categories TEXT, note TEXT," +
            "frequences TEXT";


    SqlHelper() {
        super();
    }
}
