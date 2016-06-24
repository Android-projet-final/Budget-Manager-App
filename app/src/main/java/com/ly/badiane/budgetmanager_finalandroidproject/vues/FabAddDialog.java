package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;

/**
 * Created by layely on 6/23/16.
 */
public class FabAddDialog extends Dialog implements View.OnClickListener {

    Context context;

    private Button buttonAddBudget, buttonAddExpensise;

    public FabAddDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fab_add_pop_up);

        buttonAddBudget = (Button) findViewById(R.id.dialog_bnt_budget);
        buttonAddExpensise = (Button) findViewById(R.id.dialog_bnt_depense);

        buttonAddBudget.setOnClickListener(this);
        buttonAddExpensise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent activitySwitcher;
        switch (v.getId()) {
            case R.id.dialog_bnt_budget:
                activitySwitcher = new Intent(context, TransactionActivity.class);
                activitySwitcher.putExtra("type", Transaction.ENTREE);
                activitySwitcher.putExtra("titleResID", R.string.ajuster_budget);
                context.startActivity(activitySwitcher);
                break;
            case R.id.dialog_bnt_depense:
                activitySwitcher = new Intent(context, TransactionActivity.class);
                activitySwitcher.putExtra("type", Transaction.SORTIE);
                activitySwitcher.putExtra("titleResID", R.string.ajouter_depense);
                context.startActivity(activitySwitcher);
                break;
        }
        dismiss();
    }
}
