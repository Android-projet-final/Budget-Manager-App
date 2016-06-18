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
}
