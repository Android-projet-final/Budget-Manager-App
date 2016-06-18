package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by badiane on 14/06/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    public static final String TABLE_TRANSACTION = "Transaction";
    public static final String TABLE_MOIS_ECOULES = "MoisEcoules";
    public static final String TABLE_UTILITAIRE = "Utilitaire";
    public static final String COLUMN_TRANSACTION_ID = "trans_id";
    public static final String COLUMN_TRANSACTION_TYPE = "type";
    public static final String COLUMN_TRANSACTION_MONTANT = "montant";
    public static final String COLUMN_TRANSACTION_CATEGORIE = "categorie";
    public static final String COLUMN_TRANSACTION_NOTE = "note";
    public static final String COLUMN_TRANSACTION_DATE = "date";
    public static final String COLUMN_TRANSACTION_FREQUENCE = "frequence";
    public static final String COLUMN_MOIS_ECOULES_ID = "mois_id";
    public static final String COLUMN_MOIS_ECOULES_MOIS = "mois"; //format MM/YYYY
    private final static int VersionDataBase = 2;//Verion of database
    private final static String databaseName = "budgetmanager.db";//name of database

    public SqlHelper(Context context) {
        super(context, databaseName, null, VersionDataBase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        requete de creation de la table Transaction
        String requeteCreationTableTransaction = "CREATE TABLE " + TABLE_TRANSACTION + " (" +
                COLUMN_TRANSACTION_ID + " INTEGER AUTOINCREMENT PRIMARY KEY," +
                COLUMN_TRANSACTION_TYPE + " INTEGER, " +
                COLUMN_TRANSACTION_MONTANT + " REAL, " +
                COLUMN_TRANSACTION_CATEGORIE + " TEXT, " +
                COLUMN_TRANSACTION_NOTE + " TEXT," +
                COLUMN_TRANSACTION_DATE + " TEXT, " +
                COLUMN_TRANSACTION_FREQUENCE + " INTEGER" +
                ")";

        //requete de creation de la table des mois qui se sont écoulés
        String requeteCreationTableMoisEcoules = "CREATE TABLE " + TABLE_MOIS_ECOULES + " (" +
                COLUMN_MOIS_ECOULES_ID + " INTEGER AUTOINCREMENT PRIMARY KEY, " +
                COLUMN_MOIS_ECOULES_MOIS + " TEXT)";

//        requete de creation de la table Utilitaire contenant quelques données utiles
        String requeteCreationTableUtilitaire = "CREATE TABLE " + TABLE_UTILITAIRE + " (util_id Text PRIMARY KEY, valeur Text)";


        db.execSQL(requeteCreationTableTransaction);
        db.execSQL(requeteCreationTableMoisEcoules);
        db.execSQL(requeteCreationTableUtilitaire);

//      Ajout de la ligne qui contient le nombre de fois que l'application a été lancé
        String requeteAjoutLigne_nb_lancement_app = "INSERT INTO " + TABLE_UTILITAIRE + " (util_id, valeur) values ('nb_lancement_app', '0')";
        db.execSQL(requeteAjoutLigne_nb_lancement_app);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String requeteDeMisAjourTableTransaction = "DROP TABLE IF EXISTS " + TABLE_TRANSACTION;
        String requeteDeMisAjourTableMoisEcoules = "DROP TABLE IF EXISTS " + TABLE_MOIS_ECOULES;
        String requeteDeMisAjourTableUtilitaire = "DROP TABLE IF EXISTS " + TABLE_UTILITAIRE;

        db.execSQL(requeteDeMisAjourTableTransaction);
        db.execSQL(requeteDeMisAjourTableMoisEcoules);
        db.execSQL(requeteDeMisAjourTableUtilitaire);
        onCreate(db);
    }

}