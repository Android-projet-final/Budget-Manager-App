package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.Calendar;

public class DepenseActivity extends Activity {

    private final static Calendar calendar = Calendar.getInstance();
    private static EditText date ;
    //la date en entier
    private  int jour;
    private  int  mois;
    private  int annee;
    private EditText montantedi;
    private EditText note;
    private Button validation;
    private Spinner freqeuecences;
    private Spinner liscateogries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);
        setElement();
        setAction();
    }


    private void setElement(){
        montantedi = (EditText)findViewById(R.id.editmontant);
        liscateogries = (Spinner)findViewById(R.id.textlist);
        validation = (Button)findViewById(R.id.buttonvalider);
        date = (EditText)findViewById(R.id.editdate);
        note = (EditText)findViewById(R.id.editnote);
        freqeuecences = (Spinner) findViewById(R.id.edittextFrequnces);
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);

        date.setText(jour+"/"+(mois+1)+"/"+annee);
    }
    private void setAction() {
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        freqeuecences.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tabfreq []= getResources().getStringArray(R.array.arrayfrequences);
                Toast.makeText(getApplicationContext(),tabfreq[position],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        liscateogries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tabCat[] = getResources().getStringArray(R.array.arrayCategoris);
                Toast.makeText(getApplicationContext(),tabCat[position],Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showDatePickerDialog() {
        DateSetFragmentDialog newFragment = new DateSetFragmentDialog();
        newFragment.show(getFragmentManager(),"DatePicker");
    }

    //class interne pour faire un dialog avec datepicker



    public static class DateSetFragmentDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private int jour = calendar.get(Calendar.DAY_OF_MONTH);
        private int mois = calendar.get(Calendar.MONTH);
        private int annee = calendar.get(Calendar.YEAR);
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new DatePickerDialog(getActivity(), this, annee, mois, jour);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.setText(day+"/"+(month+1)+"/"+year);
        }

    }


}
