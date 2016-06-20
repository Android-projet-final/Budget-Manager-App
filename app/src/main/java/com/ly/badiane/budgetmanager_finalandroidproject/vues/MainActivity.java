package com.ly.badiane.budgetmanager_finalandroidproject.vues;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ly.badiane.budgetmanager_finalandroidproject.R;
import com.ly.badiane.budgetmanager_finalandroidproject.activites.SettingsActivity;
import com.ly.badiane.budgetmanager_finalandroidproject.adapteurs.ListAdapteurFinance;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Mois;
import com.ly.badiane.budgetmanager_finalandroidproject.divers.Utilitaire;
import com.ly.badiane.budgetmanager_finalandroidproject.finances.Transaction;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.MoisEcoulesDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.TransactionDAO;
import com.ly.badiane.budgetmanager_finalandroidproject.sql.UtilitaireDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Context mainContext;
    protected static TransactionDAO transactionDAO;
    private static MoisEcoulesDAO moisEcoulesDAO;
    private Intent activitySwitcher; //pour changer d'activiter
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private UtilitaireDAO utilitaireDAO;
    //    private int nbSlides = 0; //Contient le nombre de pages ou de slides ou encore de tabulation dans le ViewPager
    private ArrayList<Mois> moisEcoulesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.WHITE);

        mainContext = this;

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


        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(moisEcoulesList.size() - 1);


    }

    private void premierLancementinsertion() {
        moisEcoulesDAO.insertionLorsDuPremierLancement();
    }

    private void initMoisEcoulesList() {
        moisEcoulesList = moisEcoulesDAO.liste();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                activitySwitcher = new Intent(this, SettingsActivity.class);
                startActivity(activitySwitcher);
                return true;
            case R.id.action_alarm:
                return true;
            case R.id.addbudget:
                activitySwitcher = new Intent(this, TransactionActivity.class);
                activitySwitcher.putExtra("type", Transaction.ENTREE);
                startActivity(activitySwitcher);
                return true;
            case R.id.adddepenses:
                activitySwitcher = new Intent(this, TransactionActivity.class);
                activitySwitcher.putExtra("type", Transaction.SORTIE);
                startActivity(activitySwitcher);
                return true;

        }

        return super.onOptionsItemSelected(item);
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
            Mois moisDuFragment;
            List<Transaction> listDesTransactionsDuSlide;

            if (numSlide < listeDesMois.size()) {
                moisDuFragment = listeDesMois.get(numSlide - 1);
                listDesTransactionsDuSlide = transactionDAO.listDuMois(moisDuFragment);
            } else {
                // Page Futures transaction
                Mois ceMoisCi = listeDesMois.get(listeDesMois.size() - 1);
                listDesTransactionsDuSlide = transactionDAO.listApresMois(ceMoisCi);
            }

            TextView textSectionLabel = (TextView) rootView.findViewById(R.id.section_label);
            textSectionLabel.setText(getString(R.string.section_format, numSlide));

            TextView textTotalDepense = (TextView) rootView.findViewById(R.id.text_total_depense);

            TextView textTotalBudget = (TextView) rootView.findViewById(R.id.text_total_budget);

            TextView textViewDifference = (TextView) rootView.findViewById(R.id.text_difference);

            ListView listView = (ListView) rootView.findViewById(R.id.listview);
            listView.setAdapter(new ListAdapteurFinance(mainContext, listDesTransactionsDuSlide));

            TextView emptyView = new TextView(mainContext);
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
            // Show 3 total pages.
            return moisEcoulesList.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == moisEcoulesList.size())
                return "Mois Futures"; //TODO internasionalisation
            if (position == moisEcoulesList.size() - 1)
                return "Ce mois-ci"; //TODO internasionalisation
            if (position == moisEcoulesList.size() - 2)
                return "Mois dernier";//TODO INTERNATIONALISATION
            return moisEcoulesList.get(position).toString();
        }
    }
}