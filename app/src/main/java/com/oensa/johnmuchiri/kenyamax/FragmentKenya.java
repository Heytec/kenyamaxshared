package com.oensa.johnmuchiri.kenyamax;


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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_ACTORS;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_ANGASKY;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_CENTURY_CINEMAX_JUNCTION;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_FOX_CINEPLEX_SARIT;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_GENRE;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_IMAX;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_KENYA_CINEMA;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_MOVIES;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_NYALI_CINEMAX;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_PLANET_MEDIA_CINEMA_WESTAGTE_MALL;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_PLANET_MEDIA_PRESTIGE_PLAZA;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_POSTERS;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_RATINGS;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_SYNOPSIS;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_THEATRES_SHOWING;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_TITLE;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_VOTES;
import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_YEAR;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentKenya#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentKenya extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private RecyclerView mRecyclerDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private AdapterDrawerseri mAdapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View mContainer;
    private boolean mDrawerOpened = false;
    private SparseBooleanArray selectedItems;
    Context context;
    Toolbar toolbar;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentKenya.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentKenya newInstance(String param1, String param2) {
        FragmentKenya fragment = new FragmentKenya();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentKenya() {
        // Required empty public constructor
    }


    public List<Information> getData() {
        //load only static data inside a drawer
        List<Information> data = new ArrayList<>();
        int[] icons = {R.mipmap.ic_launcher,  R.mipmap.ic_launcher, R.mipmap.ic_launcher ,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        String[] titles = getResources().getStringArray(R.array.drawer_tabse);
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }











































    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_kenya, container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerDrawer = (RecyclerView) view.findViewById(R.id.drawerList);
        mAdapter = new AdapterDrawerseri(getActivity(), getData());
        mRecyclerDrawer.setAdapter(mAdapter);
        mRecyclerDrawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerDrawer.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerDrawer, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if(position ==1){
                     //Imax 20th c
                    Intent start = new Intent(getActivity(),Imax.class);
                    startActivity(start);



                }
                else if (position==2){

                    Intent start = new Intent(getActivity(),FoxCineplexSaritCentre.class);
                    startActivity(start);

                }


                else if( position==3){

                    Intent start = new Intent(getActivity(),CenturyCinemaxJunction.class);
                    startActivity(start);


                }

                else if(position== 4) {
                    Intent start = new Intent(getActivity(),AngaSkyCinema.class);
                    startActivity(start);

                }else  if(position==5){

                    Intent start = new Intent(getActivity(),PlanetMediaPrestigePlaza.class);
                    startActivity(start);


                }else if(position==6){
                    Intent start = new Intent(getActivity(),NyaliCinemaxMombasa.class);
                    startActivity(start);




                }else if(position==7){
                    Intent start = new Intent(getActivity(),PlanetMediaKisumu.class);
                    startActivity(start);



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
