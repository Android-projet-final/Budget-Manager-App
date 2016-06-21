package com.ly.badiane.budgetmanager_finalandroidproject.divers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by layely on 6/18/16.
 */
public class Utilitaire {

    public static String MY_LOG = "MY_LOG";

    /*
    Converti une chaine de format "aa/mm/aaaa" en un objet GregorianCalandar qu'elle retourne
     */
    public static GregorianCalendar stringToCalandar(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    /*
    Converti un objet GregorianCalandar en une chaine de format "aa/mm/aaaa" qu'elle retourne
     */
    public static String calandarToString(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String calandarStr = dateFormat.format(calendar.getTime());
        return calandarStr;
    }

    public static String calendarToCompleteString(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yyyy HH:mm"));
        String calandarStr = dateFormat.format(calendar.getTime());
        return calandarStr;
    }

    public static Calendar completeStringToCalandar(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = dateFormat.parse(dateStr);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    public static int compare(Calendar calendarA, Calendar calendarB) {

        calendarA.set(Calendar.SECOND, 0);
        calendarB.set(Calendar.SECOND, 0);
        calendarA.set(Calendar.MILLISECOND, 0);
        calendarB.set(Calendar.MILLISECOND, 0);

        return calendarA.compareTo(calendarB);
    }

    public static boolean equals(Calendar a, Calendar b) {
        return compare(a, b) == 0;
    }

}
