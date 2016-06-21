package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.adapteurs.AdapteurCategorie;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Categorie;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.TransactionDAO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionActivity extends AppCompatActivity {

    private final static Calendar calendar = Calendar.getInstance();
    private static EditText datePicker;
    protected String todayStr = null;
    private int jour;
    private int mois;
    private int annee;
    private EditText editTextmontant;
    private EditText editTextNote;
    private Spinner spinnerFrequence;
    private Spinner spinnerCategorie;

    private TransactionDAO transactionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        getSupportActionBar().setTitle(getIntent().getIntExtra("titleResID", 0));

        transactionDAO = new TransactionDAO(this);

        setElement();
        setAction(); //set up listeners
    }

    private void setElement() {
        todayStr = getResources().getString(R.string.today);

        datePicker = (EditText) findViewById(R.id.datebudget);
        /*this is for bug correction*/
        datePicker.setInputType(InputType.TYPE_NULL);
        datePicker.setFocusable(false);
        /******/
        editTextmontant = (EditText) findViewById(R.id.montbudget);
        spinnerFrequence = (Spinner) findViewById(R.id.freqbudget);

        spinnerCategorie = (Spinner) findViewById(R.id.catbubget);

        ArrayList<Categorie> list;
        if (getIntent().getIntExtra("type", 0) == Transaction.ENTREE)
            list = Categorie.ALL_BUD;
        else
            list = Categorie.ALL_EXP;


        spinnerCategorie.setAdapter(new AdapteurCategorie(this, R.layout.item_categories, R.id.txtcat, list));
        editTextNote = (EditText) findViewById(R.id.notebudget);

        //la datePicker courante
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.setText(todayStr);
    }

    private void setAction() {

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        spinnerFrequence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tabfreq[] = getResources().getStringArray(R.array.arrayfrequences);
                Toast.makeText(getApplicationContext(), tabfreq[position], Toast.LENGTH_LONG).show();//TEST
                //TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinnerCategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tabCat[] = getResources().getStringArray(R.array.arrayCategoris);
//                //TODO
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    private void buttonValiderAction() {
        double montant = 0;
        try {
            montant = (double) Integer.parseInt(editTextmontant.getText().toString());
        } catch (Exception e) {
            montant = (double) Integer.parseInt(editTextmontant.getHint().toString());
            editTextmontant.setHintTextColor(Color.RED);
            Toast.makeText(this, R.string.montant_erreur, Toast.LENGTH_LONG).show();
            return;
        }
        Categorie categorie = (Categorie) spinnerCategorie.getSelectedItem();
        String note = editTextNote.getText().toString();

        GregorianCalendar date = null;
        try {
            String dateStr = datePicker.getText().toString();
            if (dateStr.equalsIgnoreCase(todayStr))
                date = new GregorianCalendar();
            else
                date = Utilitaire.stringToCalandar(datePicker.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int frequence = Transaction.JOURNALIER + spinnerFrequence.getSelectedItemPosition();

        int typeDeTransaction = getIntent().getIntExtra("type", -1); //Entree ou Sortie
        Transaction transaction = new Transaction(typeDeTransaction, montant, categorie, note, date, frequence);

        transactionDAO.ajouterTransaction(transaction);
        finish();
    }


    private void showDatePickerDialog() {
        DateSetForBudget newFragment = new DateSetForBudget();
        newFragment.show(getFragmentManager(), "DatePicker");
    }
// Cette n'a pas besoin d'etre edité sauf en cas de D'erreur à signaler
    //class interne pour faire un dialog avec datepicker

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_valider:
                buttonValiderAction();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class DateSetForBudget extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private int jour = calendar.get(Calendar.DAY_OF_MONTH);
        private int mois = calendar.get(Calendar.MONTH);
        private int annee = calendar.get(Calendar.YEAR);

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new DatePickerDialog(getActivity(), this, annee, mois, jour);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            datePicker.setText(day + "/" + (month + 1) + "/" + year);
            GregorianCalendar calendarToday = new GregorianCalendar();

            GregorianCalendar datePicked = new GregorianCalendar();
            datePicked.set(year, month, day);
//            if (calendarToday.equals(datePicked))
//                datePicker.setText(getResources().getString(R.string.today));
        }
    }

}