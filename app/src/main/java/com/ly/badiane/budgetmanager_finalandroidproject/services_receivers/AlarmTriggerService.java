package com.ly.badiane.budgetmanager_finalandroidproject.services_receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.AlarmDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.TransactionDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.vues.MainActivity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class AlarmTriggerService extends Service {

    public AlarmTriggerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread t = new Thread(new Routine(startId));
        t.start();
        return START_STICKY;
    }

    private List<Transaction> getTransactionListToNotif() {
        AlarmDAO alarmDAO = new AlarmDAO(this);
        TransactionDAO transDAO = new TransactionDAO(this);
        List<Transaction> list = new ArrayList<>();
        for (int i : alarmDAO.idAlarmList(new GregorianCalendar())) {
            list.add(transDAO.getTransaction(i));
            alarmDAO.supprimer(i);
        }
        return list;
    }

    public void showNotification(Transaction t) {

        int NotifID = new Random().nextInt(10000);
        Context context = this;
        Resources res = getResources();

        String reminderText = res.getString(R.string.reminder_text, t.getMontant().toString(), Utilitaire.calandarToString(t.getDate()));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setTicker(reminderText)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(reminderText)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);
        //Vibration
        notificationBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        //Default sound set
        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND);

        //LED
        notificationBuilder.setLights(Color.RED, 3000, 3000);

        notificationManager.notify(NotifID, notificationBuilder.getNotification());
    }

    class Routine implements Runnable {

        int serviceId;

        Routine(int serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            synchronized (this) {

                while (true) {
                    List<Transaction> transact = getTransactionListToNotif();
                    Log.i(Utilitaire.MY_LOG, "from service : " + String.valueOf(transact.size()));
                    for (Transaction t : transact) {
                        showNotification(t);
                    }
                    sleep();
                }
            }
        }

        private void sleep() {
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
