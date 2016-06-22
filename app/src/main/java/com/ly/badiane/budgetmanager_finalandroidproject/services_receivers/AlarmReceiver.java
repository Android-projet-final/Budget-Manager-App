package com.ly.badiane.budgetmanager_finalandroidproject.services_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented")
// ;
        Intent i = new Intent("com.ly.badiane.budgetmanager_finalandroidproject.services.AlarmTriggerService");
        i.setClass(context, AlarmTriggerService.class);
        context.startService(i);
    }
}
