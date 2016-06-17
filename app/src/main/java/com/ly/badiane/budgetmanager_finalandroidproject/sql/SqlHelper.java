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

    public SqlHelper(Context context) {
        super(context, databaseName, null, VersionDataBase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        requete de creation de la table Transaction
        String requeteCreationTableTransaction = "CREATE TABLE Transaction (" +
                "trans_id INTEGER AUTOINCREMENT PRIMARY KEY," +
                "type INTEGER, " +
                "montant REAL, " +
                "categories TEXT, " +
                "note TEXT," +
                "date TEXT, " +
                "frequences INTEGER" +
                ")";

        //requete de creation de la table des mois qui se sont écoulés
        String requeteCreationTableMoisEcoules = "CREATE TABLE MoisEcoules (mois_id INTEGER AUTOINCREMENT PRIMARY KEY, mm_aaaa TEXT)";

//        requete de creation de la table Utilitaire contenant quelques données utiles
        String requeteCreationTableUtilitaire = "CREATE TABLE Utilitaire (util_id Text PRIMARY KEY, valeur Text)";


        db.execSQL(requeteCreationTableTransaction);
        db.execSQL(requeteCreationTableMoisEcoules);
        db.execSQL(requeteCreationTableUtilitaire);

//      Ajout de la ligne qui contient le nombre de fois que l'application a été lancé
        String requeteAjoutLigne_nb_lancement_app = "INSERT INTO Utilitaire (util_id, valeur) values ('nb_lancement_app', '0')";
        db.execSQL(requeteAjoutLigne_nb_lancement_app);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String requeteDeMisAjourTableTransaction = "DROP TABLE IF EXISTS Transaction";
        String requeteDeMisAjourTableMoisEcoules = "DROP TABLE IF EXISTS MoisEcoules";
        String requeteDeMisAjourTableUtilitaire = "DROP TABLE IF EXISTS Utilitaire";

        db.execSQL(requeteDeMisAjourTableTransaction);
        onCreate(db);
    }

}