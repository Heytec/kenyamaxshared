package com.oensa.johnmuchiri.kenyamax;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

import java.util.ArrayList;

import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_CATEGORY;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_DESCRIPTION;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_ENDING_DATE;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_SOCIAL;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_ID;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_LOCATION;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_POSTER;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_PRICE;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_STARTING_DATE;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_TIME;
import static com.oensa.johnmuchiri.kenyamax.KeysSocial.EndpointBoxOffice.KEY_TITLE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSocialEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSocialEvents extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<EventFreeModel> listeventfree = new ArrayList<>();
    private RecyclerView listMovieHits;
    private StaggeredGridLayoutManager mLayoutManager;
    public static final String URL_FREE="http://kenyamax.oensa.com/v3/index.php/events/social/?key=850b17f83631daf62ec8ff3e7214b753";
    private static final String STATE_GIGS = "state_gigs";
    public static final String EVENT_TRANSFER_KENYA="EVENT_TRANSFER";

    private AdapterEvent adapterEvent;

    private TextView textVolleyError ;
    Button button;
    ProgressBar progressBar;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSocialEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSocialEvents newInstance(String param1, String param2) {
        FragmentSocialEvents fragment = new FragmentSocialEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentSocialEvents() {
        // Required empty public constructor
    }


    public static String getRequestUrls(){
        return URL_FREE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton=VolleySingleton.getInstance();
        requestQueue=volleySingleton.getRequestQueue();
    }



    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                getRequestUrls(),
                (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //parseJSONResponse(response);
                        //L.t(getActivity(), response.toString());


                        textVolleyError.setVisibility(View.GONE);
                        button.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);


                        listeventfree=parseJSONResponse(response);
                        adapterEvent.setMovieList(listeventfree);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                handleVolleyError(error);


            }
        });
        requestQueue.add(request);
    }





    private void handleVolleyError(VolleyError error){
        textVolleyError.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                textVolleyError.setVisibility(View.INVISIBLE);
                sendJsonRequest();
            }
        });


        progressBar.setVisibility(View.INVISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            textVolleyError.setText(R.string.error_timeout);

        } else if (error instanceof AuthFailureError) {
            textVolleyError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            textVolleyError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            textVolleyError.setText(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            textVolleyError.setText(R.string.error_parser);
            //TODO
        }

    }



    private  ArrayList<EventFreeModel> parseJSONResponse(JSONObject response) {
        ArrayList<EventFreeModel>listeventfree=new ArrayList<>();
        if (response == null || response.length() == 0) {
            return  null;
        }
        try {
            //StringBuilder data= new StringBuilder();
            JSONArray arrayGigs = response.getJSONArray(KEY_SOCIAL);
            for (int i = 0; i < arrayGigs.length(); i++) {
                JSONObject currentGigs = arrayGigs.getJSONObject(i);
                //get the id of the current movie

                //String id= currentGigs.getString(KEY_ID);
                //data.append(id + "\n");
                String id= currentGigs.getString(KEY_ID);
                String title= currentGigs.getString(KEY_TITLE);
                String time= currentGigs.getString(KEY_TIME);
                String StartingDate= currentGigs.getString(KEY_STARTING_DATE);
                String EndingDate= currentGigs.getString(KEY_ENDING_DATE);
                String location= currentGigs.getString(KEY_LOCATION);
                String price= currentGigs.getString(KEY_PRICE);
                String poster= currentGigs.getString(KEY_POSTER);
                String description= currentGigs.getString(KEY_DESCRIPTION);
                String category= currentGigs.getString(KEY_CATEGORY);



                EventFreeModel gigsevent = new EventFreeModel();

                gigsevent.setId(id);
                gigsevent.setTitle(title);
                gigsevent.setTime(time);
                gigsevent.setStartingDate(StartingDate);
                gigsevent.setEndingDate(EndingDate);
                gigsevent.setLocation(location);
                gigsevent.setPrice(price);
                gigsevent.setPoster(poster);
                gigsevent.setDescription(description);
                gigsevent.setCategory(category);


                listeventfree.add(gigsevent);


            }
            //L.T(getActivity(), listeventfree.toString());
            //L.T(getActivity(), data.toString());


        }
        catch (JSONException e) {

        }
        return listeventfree;
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelableArrayList(STATE_GIGS, listeventfree);
    }









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_fragment_social_events, container, false);



        listMovieHits = (RecyclerView) view.findViewById(R.id.listMovieHits);
        //listMovieHits.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        listMovieHits.setLayoutManager(mLayoutManager);

        adapterEvent = new AdapterEvent(getActivity());

        listMovieHits.setAdapter(adapterEvent);
        sendJsonRequest();

        textVolleyError= (TextView) view.findViewById(R.id.textVolleyError);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        button=(Button)view.findViewById(R.id.button);
        button.setVisibility(view.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);



        if(savedInstanceState!=null)
        {

            progressBar.setVisibility(View.INVISIBLE);
            button.setVisibility(View.GONE);
            listeventfree=savedInstanceState.getParcelableArrayList(STATE_GIGS);
            adapterEvent.setevents(listeventfree);
        }
        else{
            sendJsonRequest();
        }



        listMovieHits.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), listMovieHits, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), KenyaEventDetailActivity.class);
                intent.putExtra(EVENT_TRANSFER_KENYA,adapterEvent.getEventKenya(position));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));






        return view;
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
