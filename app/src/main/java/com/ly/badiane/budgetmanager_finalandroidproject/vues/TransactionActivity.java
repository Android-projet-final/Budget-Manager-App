package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionActivity extends AppCompatActivity {

    private final static Calendar calendar = Calendar.getInstance();
    private static EditText datePicker;
    protected String todayStr = null;
    private int jour;
    private int mois;
    private int annee;
    private EditText montbudget;
    private EditText editTextNote;
    private Button valbudget;
    private Spinner freqbudget;
    private Spinner lisbudget;

    private TransactionDAO transactionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

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
        montbudget = (EditText) findViewById(R.id.montbudget);
        freqbudget = (Spinner) findViewById(R.id.freqbudget);

        lisbudget = (Spinner) findViewById(R.id.catbubget);
        Categorie[] categ = {};
        lisbudget.setAdapter(new AdapteurCategorie(this, R.layout.item_categories, R.id.txtcat, Categorie.ALL));
        valbudget = (Button) findViewById(R.id.valbudget);
        editTextNote = (EditText) findViewById(R.id.notebudget);

        //la datePicker courante
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);
//        datePicker.setText(jour + "/" + (mois + 1) + "/" + annee);
        datePicker.setText(todayStr);
    }

    private void setAction() {
        valbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonValiderAction();
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                //TODO:
            }
        });

        freqbudget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        lisbudget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tabCat[] = getResources().getStringArray(R.array.arrayCategoris);
                Toast.makeText(getApplicationContext(), tabCat[position], Toast.LENGTH_LONG).show();//TEST
                //TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void buttonValiderAction() {
        double montant = (double) Integer.parseInt(montbudget.getText().toString());
        Categorie categorie = ((Categorie) lisbudget.getSelectedItem());
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

        int frequence = Transaction.JOURNALIER + freqbudget.getSelectedItemPosition();

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