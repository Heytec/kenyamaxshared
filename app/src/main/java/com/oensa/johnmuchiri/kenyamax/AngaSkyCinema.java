package com.oensa.johnmuchiri.kenyamax;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

import static com.oensa.johnmuchiri.kenyamax.KeysKenya.EndpointBoxOffice.KEY_THEATRES_SHOWING;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_ANGASKY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_TITLE;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_YEAR;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_GENRE;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_SYNOPSIS;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_ACTORS;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_RATINGS;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_VOTES;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_POSTERS;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_TIME_SHOWING;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_MONDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_TUESDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_WENESDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_THURSDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_FRIDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_SATARDAY;
import static com.oensa.johnmuchiri.kenyamax.KeysMovieTheatre.EndpointBoxOffice.KEY_SUNDAY;



public class AngaSkyCinema extends ActionBarActivity {

    Toolbar toolbar;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<MovieTheatreModel> listeventfree = new ArrayList<>();
    private RecyclerView listMovieHits;
    private StaggeredGridLayoutManager mLayoutManager;
    public static final String URL_FREE="http://kenyamax.oensa.com/v4/index.php/anga_sky/?key=$2y$10$5ebdde1aedb51ab740332uwVI2O";
    private static final String STATE_MOVIE = "state_gigs";
    public static final  String MOVIE_TRANSFER_KENYA="EVENT_TRANSFER";

    private AdapterKenyaMovies adapterKenyaMovies;

    private TextView textVolleyError ;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anga_sky_cinema);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //toolbar.getBackground().setAlpha(0);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        volleySingleton=VolleySingleton.getInstance();
        requestQueue=volleySingleton.getRequestQueue();

        textVolleyError= (TextView)findViewById(R.id.textVolleyError);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        button=(Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);




        listMovieHits = (RecyclerView) findViewById(R.id.listMovieHits);
        //listMovieHits.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        listMovieHits.setLayoutManager(mLayoutManager);

        adapterKenyaMovies = new AdapterKenyaMovies(this);

        listMovieHits.setAdapter(adapterKenyaMovies);






        if(savedInstanceState!=null)
        {

            progressBar.setVisibility(View.INVISIBLE);
            button.setVisibility(View.GONE);
            listeventfree=savedInstanceState.getParcelableArrayList(STATE_MOVIE);
            adapterKenyaMovies.setMovies(listeventfree);
        }
        else{
            sendJsonRequest();
        }







        listMovieHits.addOnItemTouchListener(new RecyclerTouchListener(this, listMovieHits, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(AngaSkyCinema.this,KenyaMovieTheatreDetails.class);
                intent.putExtra(MOVIE_TRANSFER_KENYA, adapterKenyaMovies.getMovieKenya(position));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public static String getRequestUrls(){
        return URL_FREE;
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
                        adapterKenyaMovies.setMovieList(listeventfree);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                handleVolleyError(error);


            }
        });
        requestQueue.add(request);
    }




    private  ArrayList<MovieTheatreModel> parseJSONResponse(JSONObject response) {
        ArrayList<MovieTheatreModel>listeventfree=new ArrayList<>();
        if (response == null || response.length() == 0) {
            return  null;
        }
        try {
            //StringBuilder data= new StringBuilder();
            JSONArray arrayGigs = response.getJSONArray(KeyAngaSky.EndpointBoxOffice.KEY_MOVIES);
            for (int i = 0; i < arrayGigs.length(); i++) {
                JSONObject currentGigs = arrayGigs.getJSONObject(i);
                //get the id of the current movie

                //String id= currentGigs.getString(KEY_ID);
                //data.append(id + "\n");
                //String id= currentGigs.getString(KEY_ID);
                String title= currentGigs.getString(KEY_TITLE);
                String year= currentGigs.getString(KEY_YEAR);
                String genre= currentGigs.getString(KEY_GENRE);
                String synopsis= currentGigs.getString(KEY_SYNOPSIS);
                String actors= currentGigs.getString(KEY_ACTORS);
                String imbdrating= currentGigs.getString(KEY_RATINGS);
                String imbdvotes= currentGigs.getString(KEY_VOTES);
                String poster= currentGigs.getString(KEY_POSTERS);
                String timeshowing= currentGigs.getString(KEY_TIME_SHOWING);


                JSONObject Keytheatre= currentGigs.getJSONObject(KEY_TIME_SHOWING);
                String monday=null;
                if(Keytheatre.has(KEY_MONDAY)){

                    monday=   Keytheatre.getString(KEY_MONDAY);
                }else{
                    monday="no action";
                }

                String tuesday=null;
                if(Keytheatre.has(KEY_TUESDAY)){

                    tuesday=   Keytheatre.getString(KEY_TUESDAY);
                }else{
                    tuesday="no action";
                }


                String wednesday=null;
                if(Keytheatre.has(KEY_WENESDAY)){

                    wednesday=   Keytheatre.getString(KEY_WENESDAY);
                }else{
                    wednesday="no action";
                }


                String thursday=null;
                if(Keytheatre.has(KEY_THURSDAY)){

                    thursday=   Keytheatre.getString(KEY_THURSDAY);
                }else{
                    thursday="no action";
                }


                String Friday=null;
                if(Keytheatre.has(KEY_FRIDAY)){

                    Friday=   Keytheatre.getString(KEY_FRIDAY);
                }else{
                    Friday="no action";
                }


                String satarday=null;
                if(Keytheatre.has(KEY_SATARDAY)){

                    satarday=   Keytheatre.getString(KEY_SATARDAY);
                }else{
                    satarday="no action";
                }



                String sunday=null;
                if(Keytheatre.has(KEY_SUNDAY)){

                    sunday=   Keytheatre.getString(KEY_SUNDAY);
                }else{
                    sunday="no action";
                }




                MovieTheatreModel movies = new MovieTheatreModel();

                movies.setTitle(title);
                movies.setYear(year);
                movies.setGenre(genre);
                movies.setSynopsis(synopsis);
                movies.setActors(actors);
                movies.setImdbrarating(imbdrating);
                movies.setImbdvotes(imbdvotes);
                movies.setPoster(poster);
                movies.setMonday(monday);
                movies.setTuesday(tuesday);
                movies.setWednesday(wednesday);
                movies.setThursday(thursday);
                movies.setFriday(Friday);
                movies.setSatarday(satarday);
                movies.setSunday(sunday);



                listeventfree.add(movies);


            }
            //L.T(getActivity(), listeventfree.toString());
            //L.T(getActivity(), data.toString());


        }
        catch (JSONException e) {

        }
        return listeventfree;
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














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anga_sky_cinema, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id ==android.R.id.home) {

            //NavUtils.navigateUpFromSameTask(this);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelableArrayList(STATE_MOVIE, listeventfree);
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
