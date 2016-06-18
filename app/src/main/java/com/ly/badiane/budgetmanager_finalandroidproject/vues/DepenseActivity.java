package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

import java.util.Calendar;

public class DepenseActivity extends Activity {

    private final static Calendar calendar = Calendar.getInstance();
    //la date en entier
    int jour;
    int mois;
    int annee;
    private EditText montantedi;
    private EditText categorirs ;
    private EditText note;
    private Button validation;
    private EditText freqeuecences;
    private EditText liscateogries;
    private EditText date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);
        setElement();
        setAction();
    }


    private void setElement(){
        montantedi = (EditText)findViewById(R.id.editmontant);
        liscateogries = (EditText)findViewById(R.id.textlist);
        validation = (Button)findViewById(R.id.buttonvalider);
        date = (EditText)findViewById(R.id.editdate);
        note = (EditText)findViewById(R.id.editnote);
        freqeuecences = (EditText)findViewById(R.id.edittextFrequnces);
        annee = calendar.get(Calendar.YEAR);
        mois = calendar.get(Calendar.MONTH);
        jour = calendar.get(Calendar.DAY_OF_MONTH);

        date.setText(jour+"/"+(mois+1)+"/"+annee);
    }
    private void setAction(){
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
             if(hasFocus){
                 DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(),
                         new DatePickerDialog.OnDateSetListener() {
                             @Override
                             public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                 date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                             }
                         },annee,mois,jour
                 );
                 datePickerDialog.show();
             }
            }
        });

    }
}
