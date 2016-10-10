package com.oensa.johnmuchiri.kenyamax;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrawer extends Fragment {

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private RecyclerView mRecyclerDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private AdapterDrawer mAdapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View mContainer;
    private boolean mDrawerOpened = false;
    private SparseBooleanArray selectedItems;
    Context context;

    public FragmentDrawer() {
        // Required empty public constructor
    }

    public List<Information> getData() {
        //load only static data inside a drawer
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.nowshowing,  R.drawable.rate, R.drawable.help };
        String[] titles = getResources().getStringArray(R.array.drawer_tabs);
        for (int i = 0; i < titles.length; i++) {
            Information information = new Information();
            information.title = titles[i];
            information.iconId = icons[i];
            data.add(information);
        }
        return data;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = MyApplication.readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, false);
        mFromSavedInstanceState = savedInstanceState != null ? true : false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerDrawer = (RecyclerView) view.findViewById(R.id.drawerList);
        mAdapter = new AdapterDrawer(getActivity(), getData());
        mRecyclerDrawer.setAdapter(mAdapter);
        mRecyclerDrawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerDrawer.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerDrawer, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                //((MainActivity) getActivity()).onDrawerItemClicked(position - 1);
                if(position ==1){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.oensa.com"));
                    startActivity(intent);



                }
                else if (position==2){
                    //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax"));
                    //startActivity(browserIntent);


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.oensa.johnmuchiri.kenyamax"));
                    startActivity(intent);
                }


                else if( position==3){
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Kenyamax at Google Play to get latest events and  movie showing in local theatre\n https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }

                else if(position== 4) {
                    //Intent start = new Intent(getActivity(),Events.class);
                    //startActivity(start);


                }else  if(position==5){



                }else if(position==6){




                }else if(position==7){



                }





                /*switch (position -1){


                    case 1:

                        break;


                    case 2:

                        break;


                    case 3:

                        break;

                    case 4:
                        //eventso

                        Intent start = new Intent(getActivity(),Events.class);
                      startActivity(start);
                        break;

                    case 5:
                        //Help


                       start = new Intent(getActivity(),Enews.class);
                        startActivity(start);

                        break;

                    case 6:
                        //rate

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oensa.com/hire"));
                        startActivity(browserIntent);
                        break;



                    case 7:
                        //share

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM,"hey are you comming ");
                        shareIntent.setType("image/*");
                        startActivity(shareIntent);




                        break;


                }*/





            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainer = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("VIVZ", "onDrawerOpened");
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                 MyApplication.saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer);
                }

                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("VIVZ", "onDrawerClosed");
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                ((MainActivity) getActivity()).onDrawerSlide(slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
               /* if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
                    mDrawerLayout.openDrawer(mContainer);
                }*/
            }
        });


    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }
}
