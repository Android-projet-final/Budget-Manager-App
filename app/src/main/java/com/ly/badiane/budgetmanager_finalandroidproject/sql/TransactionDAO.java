package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by badiane on 14/06/2016.
 */
public class TransactionDAO {
    private static String selection = "SELECT * FROM Transaction";
    private SQLiteDatabase db;

    public TransactionDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }

    public List<Transaction> recuperTouteLaListe() {
        List<Transaction> list = null;

        Cursor result = db.rawQuery("select trans_id, type, montant, categorie, note, date, frequence from etudiants", null);

        list = recuperCommeListe(result);
        result.close();
        return list;
    }


    private List<Transaction> recuperCommeListe(Cursor result) {
        List<Transaction> list = new ArrayList<>();
        if (result == null)
            return list;

        result.moveToFirst();

        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            int type = result.getInt(1);
            double montant = result.getDouble(2);
            String categorie = result.getString(3);
            String note = result.getString(4);
            String dateString = result.getString(5);
            int frequence = result.getInt(6);

            //TODO list.add(new Transaction(id, type, montant, categorie, note, date, frequence) );
            //Utiliser calandar serait mieux dans ce cas ci au lieu de date
            result.moveToNext();
        }
        return list;
    }

    public long ajouterTransaction() {
        ContentValues values = new ContentValues();
        //TODO
        return db.insert("finances", null, values);
    }

    public boolean supprimerTransaction(int id) {
        //TODO
        return true;
    }

    public boolean modifierTransaction() {
        //TODO
        return true;
    }

}