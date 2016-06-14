package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

public class BudgetActivty extends AppCompatActivity {

    private EditText montantedi;
    private EditText categorirs ;
    private EditText note;
    private Button validation;
    private EditText freqeuecences;
    private EditText liscateogries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_activty);
        setElement();
    }

    private void setElement(){
        montantedi = (EditText)findViewById(R.id.editmontant);
        liscateogries = (EditText)findViewById(R.id.editnote);
        validation = (Button)findViewById(R.id.buttonvalider);
        note = (EditText)findViewById(R.id.editnote);
        freqeuecences = (EditText)findViewById(R.id.edittextFrequnces);
    }
}
