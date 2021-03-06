package com.ly.badiane.budgetmanager_finalandroidproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categorie;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<Transaction> listeNonTriee() {
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

        list = commeList(result);
        result.close();
        return list;
    }


    private List<Transaction> commeList(Cursor result) {
        List<Transaction> list = new ArrayList<>();
        if (result == null)
            return list;

        result.moveToFirst();

        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            int type = result.getInt(1);
            double montant = result.getDouble(2);
            Categorie categorie = Categorie.getInstance(result.getInt(3));
            String note = result.getString(4);
            String dateString = result.getString(5);
            int frequence = result.getInt(6);

            GregorianCalendar date = null;
            try {
                date = Utilitaire.stringToCalandar(dateString);
                Transaction transaction = new Transaction(id, type, montant, categorie, note, date, frequence);
                list.add(transaction);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Utiliser calandar serait mieux dans ce cas ci au lieu de date
            result.moveToNext();
        }
        return list;
    }

    public Transaction getTransaction(int id) {
        Cursor result = db.rawQuery("SELECT " +
                SqlHelper.COLUMN_TRANSACTION_ID + ", " +
                SqlHelper.COLUMN_TRANSACTION_TYPE + ", " +
                SqlHelper.COLUMN_TRANSACTION_MONTANT + ", " +
                SqlHelper.COLUMN_TRANSACTION_CATEGORIE + ", " +
                SqlHelper.COLUMN_TRANSACTION_NOTE + ", " +
                SqlHelper.COLUMN_TRANSACTION_DATE + ", " +
                SqlHelper.COLUMN_TRANSACTION_FREQUENCE +
                " FROM " + SqlHelper.TABLE_TRANSACTION +
                " WHERE " + SqlHelper.COLUMN_TRANSACTION_ID + "=" + id, null);

        List<Transaction> list = commeList(result);
        if (list.size() == 1)
            return list.get(0);
        return null;
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

        int resultDeLaRequete = db.update(SqlHelper.TABLE_TRANSACTION, contentValues, SqlHelper.COLUMN_TRANSACTION_ID + "=" + transactionID, null);
        if (resultDeLaRequete == 0)
            return false;
        else
            return true;
    }

    public List<Transaction> listDuMois(Mois mois) {
        List<Transaction> list = new ArrayList<Transaction>();
        for (Transaction transac : listeNonTriee()) {
            if (mois.includes(transac.getDate()))
                list.add(transac);
        }
        return list;
    }

    public List<Transaction> listApresMois(Mois mois) {
        List<Transaction> list = new ArrayList<Transaction>();
        for (Transaction transac : listeNonTriee()) {
            if (mois.isBefore(transac.getDate()))
                list.add(transac);
        }
        return list;
    }

    public List<Transaction> listAvantMois(Mois mois) {
        List<Transaction> list = new ArrayList<Transaction>();
        for (Transaction transac : listeNonTriee()) {
            if (mois.isAfter(transac.getDate()))
                list.add(transac);
        }
        return list;
    }

    public Transaction dernierTransaction() {
        List<Transaction> list = listeNonTriee();
        int lastIndex = listeNonTriee().size() - 1;
        if (lastIndex >= 0) {
            return list.get(lastIndex);
        }
        return null;
    }

    /*
     * Retourne la liste des transactions effectuées entre un mois A et B (inclus les mois effectués pendant A et B)
    * */
    public List<Transaction> listEntre(Mois moisA, Mois moisB) {
        Mois moisInf, moisSup;
        if (moisA.isBefore(moisB)) {
            moisInf = moisA;
            moisSup = moisB;
        } else {
            moisInf = moisB;
            moisSup = moisA;
        }
        List<Transaction> list = new ArrayList<Transaction>();
        for (Transaction transac : listeNonTriee()) {
            Calendar calendar = transac.getDate();
            if ((moisInf.isBefore(calendar) || moisInf.includes(calendar)) && (moisSup.isAfter(calendar) || moisSup.includes(calendar)))
                list.add(transac);
        }
        return list;
    }

    private ContentValues getContentValuesWithoutID(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(SqlHelper.COLUMN_TRANSACTION_TYPE, transaction.getType());
        values.put(SqlHelper.COLUMN_TRANSACTION_MONTANT, transaction.getMontant());
        values.put(SqlHelper.COLUMN_TRANSACTION_CATEGORIE, transaction.getCategorie().getId());
        values.put(SqlHelper.COLUMN_TRANSACTION_NOTE, transaction.getNote());
        values.put(SqlHelper.COLUMN_TRANSACTION_DATE, Utilitaire.calandarToString(transaction.getDate()));
        values.put(SqlHelper.COLUMN_TRANSACTION_FREQUENCE, transaction.getFrequences());
        return values;
    }


    public List<Transaction> listAvantpFreqMois(Mois moisDuFragment) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction :this.listAvantMois(moisDuFragment)) {
            if(transaction.getFrequences() != Transaction.UNE_FOIS)
                list.add(transaction);
        }
        return list;
    }
}