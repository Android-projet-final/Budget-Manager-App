package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

public class DepenseActivity extends Activity {

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
    }
    private void setAction(){
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
