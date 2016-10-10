package com.oensa.johnmuchiri.kenyamax;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseAnalytics;
import com.pushbots.push.Pushbots;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    RecyclerView recyclerView;
    AdapterActivity adapterActivity;
    StaggeredGridLayoutManager mLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushbots.sharedInstance().init(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentDrawer drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        recyclerView =(RecyclerView)findViewById(R.id.drawerList);
        adapterActivity=new AdapterActivity(this,getData());
        recyclerView.setAdapter(adapterActivity);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
       recyclerView.setLayoutManager(mLayoutManager);



    }


    public static List<Information> getData(){
        //load only static data inside a drawer
        List<Information> data=new ArrayList<>();
        //int[] icons={R.drawable.event, R.drawable.arts, R.drawable.movies};
        int[] icons={R.drawable.event, R.drawable.arts, R.drawable.movies};
        //String[] titles={"Vivz","Anky","Slidenerd","YouTube"};
        for(int i=0; i<icons.length;i++)
        {
            Information current=new Information();
            current.iconId=icons[i];
            //current.title=titles[i];
            data.add(current);
        }
        return data;
    }





    public void onDrawerItemClicked(int index){


    }

    public void onDrawerSlide(float slideOffset) {

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
        if (id == R.id.action_settings) {

            Intent start =new Intent();
            start = new Intent(MainActivity.this,About.class);
            start.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(start);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
