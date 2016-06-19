package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.Calendar;

public class BudgetActivty extends AppCompatActivity {

    private final static Calendar calendar = Calendar.getInstance();
    private static EditText date ;
    private  int jour;
    private  int  mois;
    private  int annee;
    private EditText montbudget;
    private EditText note;
    private Button valbudget;
    private Spinner freqbudget;
    private Spinner lisbudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_activty);
        setElement();
        setElement();
    }

    private void setElement(){

    date = (EditText)findViewById(R.id.datebudget);
        montbudget = (EditText)findViewById(R.id.montbudget);
        freqbudget = (Spinner)findViewById(R.id.freqbudget);
        lisbudget = (Spinner) findViewById(R.id.catbubget);
        valbudget = (Button)findViewById(R.id.valbudget);
        note = (EditText)findViewById(R.id.notebudget);

        //la date courant
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);
        date.setText(jour+"/"+(mois+1)+"/"+annee);
    }
    private void setaction(){
    valbudget.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//TODO :
        }
    });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                //TODO:
            }
        });
        freqbudget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tabfreq []= getResources().getStringArray(R.array.arrayfrequences);
                Toast.makeText(getApplicationContext(),tabfreq[position],Toast.LENGTH_LONG).show();//TEST
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
                Toast.makeText(getApplicationContext(),tabCat[position],Toast.LENGTH_LONG).show();//TEST
                //TODO
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void showDatePickerDialog() {
        DateSetForBudget newFragment = new DateSetForBudget();
        newFragment.show(getFragmentManager(),"DatePicker");
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
            date.setText(day+"/"+(month+1)+"/"+year);
        }

    }
}
