package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categories;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by badiane on 14/06/2016.
 */
public class TransactionDAO {
    private SQLiteDatabase db;

    public TransactionDAO(Context context) {
        db = new SqlHelper(context).getWritableDatabase();
    }

    public List<Transaction> recuperTouteLaListe() {
        List<Transaction> list = null;

        Cursor result = db.rawQuery("select " +
                SqlHelper.COLUMN_TRANSACTION_ID + ", " +
                SqlHelper.COLUMN_TRANSACTION_TYPE + ", " +
                SqlHelper.COLUMN_TRANSACTION_MONTANT + ", " +
                SqlHelper.COLUMN_TRANSACTION_CATEGORIE + ", " +
                SqlHelper.COLUMN_TRANSACTION_NOTE + ", " +
                SqlHelper.COLUMN_TRANSACTION_DATE + ", " +
                SqlHelper.COLUMN_TRANSACTION_FREQUENCE + " from " +
                SqlHelper.TABLE_TRANSACTION + "", null);

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
            Categories categories = Categories.getInstance(result.getInt(3));
            String note = result.getString(4);
            String dateString = result.getString(5);
            int frequence = result.getInt(6);

            GregorianCalendar date = null;
            try {
                date = Utilitaire.stringToCalandar(dateString);
                Transaction transaction = new Transaction(id, type, montant, categories, note, date, frequence);
                list.add(transaction);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Utiliser calandar serait mieux dans ce cas ci au lieu de date
            result.moveToNext();
        }
        return list;
    }

    public boolean ajouterTransaction(Transaction transaction) {

        ContentValues values = getContentValuesWithoutID(transaction);
        if (values == null)
            return false;

        long resultatDeLaRequete = db.insert(SqlHelper.TABLE_TRANSACTION, null, values);
        if (resultatDeLaRequete == -1)
            return false;
        return true;
    }

    public boolean supprimerTransaction(int transactionID) {
        int resultatDeLaRequete = db.delete(SqlHelper.TABLE_TRANSACTION, SqlHelper.COLUMN_TRANSACTION_ID + "=" + transactionID, null);
        if (resultatDeLaRequete == 0)
            return false;
        return true;
    }

    public boolean modifierTransaction(int transactionID, Transaction transaction) {
        ContentValues contentValues = getContentValuesWithoutID(transaction);
        if (contentValues == null)
            return false;
        db.update(SqlHelper.TABLE_TRANSACTION, contentValues, SqlHelper.COLUMN_TRANSACTION_ID + "=" + transactionID, null);
        return true;
    }

    private ContentValues getContentValuesWithoutID(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(SqlHelper.COLUMN_TRANSACTION_TYPE, transaction.getType());
        values.put(SqlHelper.COLUMN_TRANSACTION_MONTANT, transaction.getMontant());
        values.put(SqlHelper.COLUMN_TRANSACTION_CATEGORIE, transaction.getCategories().getId());
        values.put(SqlHelper.COLUMN_TRANSACTION_NOTE, transaction.getNote());
        values.put(SqlHelper.COLUMN_TRANSACTION_DATE, Utilitaire.calandarToString(transaction.getDate()));
        values.put(SqlHelper.COLUMN_TRANSACTION_FREQUENCE, transaction.getFrequences());
        return values;
    }
}