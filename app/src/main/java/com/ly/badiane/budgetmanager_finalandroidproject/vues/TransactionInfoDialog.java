package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.AlarmDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.TransactionDAO;

/**
 * Created by layely on 6/22/16.
 */
public class TransactionInfoDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    private Transaction transaction;
    private TransactionDAO transactionDAO;
    private AlarmDAO alarmDAO;

    private Button buttonModify, buttonDelete;

    private TextView textViewType, textViewDate, textViewAlarm, textViewNote, textViewFrequence;

    private ImageView imageViewCategorie, imageViewType;

    public TransactionInfoDialog(Activity a, Transaction t, TransactionDAO transactionDAO) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.transactionDAO = transactionDAO;
        this.alarmDAO = new AlarmDAO(c);
        transaction = t;
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

        init();

        buttonDelete.setOnClickListener(this);
        buttonModify.setOnClickListener(this);
    }

    private void init() {
        buttonModify = (Button) findViewById(R.id.buttonModifyDialogTransaction);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteDialogTransaction);

        textViewType = (TextView) findViewById(R.id.textViewTypeDialogTransaction);
        textViewDate = (TextView) findViewById(R.id.textViewDateDialogTransaction);
        textViewAlarm = (TextView) findViewById(R.id.textviewAlarmDialogTransaction);
        textViewNote = (TextView) findViewById(R.id.textviewNoteTransaction);
        textViewFrequence = (TextView) findViewById(R.id.textviewFrequenceDialogTransaction);

        imageViewCategorie = (ImageView) findViewById(R.id.imageViewDialogCategTransaction);
        imageViewType = (ImageView) findViewById(R.id.imageViewTypeDialogTransaction);

        int typeResId = 0;
        int typeIconId = 0;
        if (transaction.isBudget()) {
            typeResId = R.string.budget;
            typeIconId = R.mipmap.arrow_down_green_nobg;
        } else {
            typeResId = R.string.despense;
            typeIconId = R.mipmap.arrow_red_up_nobg;
        }
        imageViewType.setImageResource(typeIconId);
        imageViewCategorie.setImageResource(transaction.getCategorie().getDrawableResId());
        textViewType.setText(typeResId);
        textViewDate.setText(Utilitaire.calandarToString(transaction.getDate()));
        textViewNote.setText(transaction.getNote());

        String alarm = alarmDAO.getDateTime(transaction.getId());
        textViewAlarm.setText(alarm);

        textViewFrequence.setText(transaction.getFrequencesResID());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonModifyDialogTransaction:
                //TODO
                break;
            case R.id.buttonDeleteDialogTransaction:
                alarmDAO.supprimer(transaction.getId());
                transactionDAO.supprimerTransaction(transaction.getId());
                c.recreate();
                break;
        }
        dismiss();
    }
}
