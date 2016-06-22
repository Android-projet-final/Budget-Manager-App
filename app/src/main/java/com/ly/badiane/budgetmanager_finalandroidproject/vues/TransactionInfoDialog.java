package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ly.badiane.budgetmanager_finalandroidproject.R;

/**
 * Created by layely on 6/22/16.
 */
public class TransactionInfoDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    Button buttonModify, buttonDelete;

    public TransactionInfoDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_transaction_info);

        DisplayMetrics dm = new DisplayMetrics();
        c.getWindowManager().getDefaultDisplay().getMetrics(dm);


        int width = dm.widthPixels;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setLayout((int) (width * 0.75), height);

        buttonModify = (Button) findViewById(R.id.buttonModifyDialogTransaction);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteDialogTransaction);

        buttonDelete.setOnClickListener(this);
        buttonModify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonModifyDialogTransaction:
            case R.id.buttonDeleteDialogTransaction:
                break;
        }
        dismiss();
    }
}
