package com.ly.badiane.budgetmanager_finalandroidproject.vues;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.adapteurs.ListTransactionAdapteur;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;
import com.ly.badiane.budgetmanager_finalandroidproject.services_receivers.AlarmTriggerService;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.MoisEcoulesDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.TransactionDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.UtilitaireDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Context mainContext;
    protected static TransactionDAO transactionDAO;
    private static MoisEcoulesDAO moisEcoulesDAO;
    int i;
    private Intent activitySwitcher; //pour changer d'activiter
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private UtilitaireDAO utilitaireDAO;
    //    private int nbSlides = 0; //Contient le nombre de pages ou de slides ou encore de tabulation dans le ViewPager
    private ArrayList<Mois> moisEcoulesList;
    private FloatingActionButton fab;

    public static void listItemClicked(Transaction transaction) {
        Dialog dialog = new TransactionInfoDialog((Activity) mainContext, transaction, transactionDAO);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainContext = this;

        if (i == 0) {
            setContentView(R.layout.activity_start);
            i++;
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        //set up DAOs for databases querries
        utilitaireDAO = new UtilitaireDAO(this);
        moisEcoulesDAO = new MoisEcoulesDAO(this);
        transactionDAO = new TransactionDAO(this);

        utilitaireDAO.mettreAjourNbLancementApp();
        if (utilitaireDAO.nombreLancementApp() == 1) {
            premierLancementinsertion();
            Log.i(Utilitaire.MY_LOG, "nombreLancementapp()");
        }

        initMoisEcoulesList();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(moisEcoulesList.size() - 1);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTitleStrip);
        pagerTabStrip.setTextColor(Color.WHITE);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor("#FF54D4"));

        startService(new Intent(this, AlarmTriggerService.class));

        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private void premierLancementinsertion() {
        moisEcoulesDAO.insertionLorsDuPremierLancement();
    }

    private void initMoisEcoulesList() {
        moisEcoulesList = moisEcoulesDAO.liste();
        Mois moisCourant = Mois.getCurrentMonth();
        Mois dernierMoisEnregistre = moisEcoulesList.get(moisEcoulesList.size() - 1);
        if (dernierMoisEnregistre.isBefore(moisCourant) || dernierMoisEnregistre.isAfter(moisCourant)) {
            Log.i(Utilitaire.MY_LOG, "dans if de initMois");
            moisEcoulesDAO.reInit();
            moisEcoulesList = moisEcoulesDAO.liste();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //no inspection SimplifiableIfStatement
        switch (id) {
            case R.id.menu_home:
                mViewPager.setCurrentItem(moisEcoulesList.size() - 1);
                return true;
            case R.id.menu_rapport:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    //floatingActionBar action
    public void showDialogAddTransaction(View view) {
        Dialog dialog = new FabAddDialog(this);
        dialog.show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            int numSlide = getArguments().getInt(ARG_SECTION_NUMBER);
            ArrayList<Mois> listeDesMois = moisEcoulesDAO.liste();
            Mois moisDuFragment = null;
            List<Transaction> listDesTransactionsDuSlide;
            Mois ceMoisCi = null;

            if (numSlide < listeDesMois.size()) {
                moisDuFragment = listeDesMois.get(numSlide);
                listDesTransactionsDuSlide = transactionDAO.listDuMois(moisDuFragment);
                List<Transaction> listDesTransactionsFreq = transactionDAO.listAvantpFreqMois(moisDuFragment);
                listDesTransactionsDuSlide.addAll(listDesTransactionsFreq);
            } else {
                // Page Futures transaction
                ceMoisCi = listeDesMois.get(listeDesMois.size() - 1);
                listDesTransactionsDuSlide = transactionDAO.listApresMois(ceMoisCi);
            }


            TextView textSectionLabel = (TextView) rootView.findViewById(R.id.section_label);
//            textSectionLabel.setText(getString(R.string.section_format, numSlide));

            TextView textTotalDepense = (TextView) rootView.findViewById(R.id.text_total_depense);

            TextView textTotalBudget = (TextView) rootView.findViewById(R.id.text_total_budget);

            TextView textViewDifference = (TextView) rootView.findViewById(R.id.text_difference);

            final ListView listView = (ListView) rootView.findViewById(R.id.listview);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listItemClicked((Transaction) listView.getItemAtPosition(position));
                }
            });
            listView.setAdapter(new ListTransactionAdapteur(mainContext, listDesTransactionsDuSlide));

            final Double totalDepense = Transaction.totalExpensese(listDesTransactionsDuSlide, moisDuFragment);
            final Double totalBudet = Transaction.totalBudget(listDesTransactionsDuSlide, moisDuFragment);
            final Double difference = totalBudet - totalDepense;

            textTotalDepense.setText(totalDepense.toString());
            textTotalBudget.setText(totalBudet.toString());
            textViewDifference.setText(difference.toString());

            if (difference < 0)
                textViewDifference.setTextColor(Color.RED);
            else
                textViewDifference.setTextColor(Color.GREEN);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return moisEcoulesList.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == moisEcoulesList.size())
                return getResources().getString(R.string.future_months);
            if (position == moisEcoulesList.size() - 1)
                return getResources().getString(R.string.this_month);
            if (position == moisEcoulesList.size() - 2)
                return getResources().getString(R.string.last_month);
            return moisEcoulesList.get(position).toString();
        }
    }
}
