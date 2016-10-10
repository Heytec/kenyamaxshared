package com.oensa.johnmuchiri.kenyamax;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class Movies extends ActionBarActivity {

    Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;


    public static final int MOVIES_SEARCH_RESULTS=0;
    public static final int MOVIES_HITS=1;
    public static final int TV_SHOWS=2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {


            return true;
        }*/


        if(id==android.R.id.home){
            //NavUtils.navigateUpFromSameTask(this);
            //onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabse);
        }

        public Fragment getItem(int num) {
            Fragment fragment=null;
            switch (num){
                case MOVIES_SEARCH_RESULTS:
                    fragment= FragmentKenya.newInstance("", "");
                    break;
                case MOVIES_HITS:
                    fragment= FragmentBoxOffice.newInstance("", "");

                    break;

                case TV_SHOWS:
                    fragment= FragmentTvshows.newInstance("", "");

                    break;



            }
            return fragment;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }



}
