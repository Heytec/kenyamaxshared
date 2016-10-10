package com.oensa.johnmuchiri.kenyamax;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.BitSet;

import static com.oensa.johnmuchiri.kenyamax.FragmentFree.EVENT_TRANSFER_KENYA;


public class KenyaMovieTheatreDetails extends ActionBarActivity implements DateRangePickerFragment.OnDateRangeSelectedListener{

    TextView movietitle,moviesynopsis ,movieyear,movierating,movievotes,synopsis;
    TextView descrion  , startingdate ,endingdate, tuesday, wednesday, thursday,friday,satarday,sunday;
    Toolbar toolbar;
    ImageView imageView;
    ImageLoader imageLoader;
    RatingBar ratingBar;
    private VolleySingleton volleySingleton;
    private ShareActionProvider miShareAction;
    MovieTheatreModel eventFreeModel;
    Button share;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_kenya_movie_theatre_details);
        setContentView(R.layout.zeric);

        //setContentView(R.layout.kenyamoviescordinatorlay);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //toolbar.getBackground().setAlpha(0);

        //share=(Button)findViewById(R.id.sharething);jb^i

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fab= (FloatingActionButton)findViewById(R.id.calender);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateRangePickerFragment dateRangePickerFragment = DateRangePickerFragment.newInstance(KenyaMovieTheatreDetails.this, false);
                dateRangePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


        Intent intent = getIntent();
        eventFreeModel = new MovieTheatreModel();
        eventFreeModel =  intent.getParcelableExtra(EVENT_TRANSFER_KENYA);


        movietitle = (TextView) findViewById(R.id.movieTitle);
        movietitle.setText(eventFreeModel.getTitle());


        movieyear = (TextView) findViewById(R.id.year);
        movieyear.setText(eventFreeModel.getYear());

        movierating = (TextView) findViewById(R.id.rating);
        movierating.setText(eventFreeModel.getImdbrarating());


        movievotes = (TextView) findViewById(R.id.votes);
        movievotes.setText(eventFreeModel.getImbdvotes());

        descrion =(TextView)findViewById(R.id.description);
        descrion.setText(eventFreeModel.getSynopsis());


        moviesynopsis = (TextView) findViewById(R.id.synopsis);
        moviesynopsis.setText(eventFreeModel.getActors());


        startingdate =(TextView)findViewById(R.id.startingdatedetails);
        startingdate.setText(eventFreeModel.getGenre());

        endingdate=(TextView)findViewById(R.id.endingdtedetails);
        endingdate.setText(eventFreeModel.getMonday());

        tuesday =(TextView)findViewById(R.id.tuesday);
        tuesday.setText(eventFreeModel.getTuesday());

        wednesday =(TextView)findViewById(R.id.Wednesday);
        wednesday.setText(eventFreeModel.getWednesday());

        thursday=(TextView)findViewById(R.id.Thursday);
        thursday.setText(eventFreeModel.getThursday());

        friday =(TextView)findViewById(R.id.Friday);
        friday.setText(eventFreeModel.getFriday());

        satarday =(TextView)findViewById(R.id.Satarday);
       satarday.setText(eventFreeModel.getSatarday());

        sunday=(TextView)findViewById(R.id.Sunday);
        sunday.setText(eventFreeModel.getSunday());


        //ratingBar=(RatingBar)findViewById(R.id.movieAudienceScore);
        //ratingBar.setRating(movie.getYear());


        imageView = (ImageView) findViewById(R.id.movieThumbnail);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();

        final String urlThumnail = eventFreeModel.getPoster();
        if (urlThumnail != null) {
            imageLoader.get(urlThumnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });



            /*Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
       shareIntent.setType("image/*");*/

            /*share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Uri imageUri = Uri.parse(urlThumnail);

                    final String urlThumnail = eventFreeModel.getPoster();
                    if(urlThumnail!=null){

                        imageLoader.get(urlThumnail, new ImageLoader.ImageListener() {
                            @Override
                            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                //imageView.setImageBitmap(response.getBitmap());




                                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                                //whatsappIntent.setType("text/plain");
                                //whatsappIntent.setPackage("com.whatsapp");
                                //whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey! let us go for " + eventFreeModel.getTitle());

                                whatsappIntent.setType("image/png");
                                //whatsappIntent.setType("text/plain");

                                whatsappIntent.putExtra(Intent.EXTRA_STREAM, response.getBitmap());
                                startActivity(Intent.createChooser(whatsappIntent, "Share image using"));
                                //whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey! let us go for " + eventFreeModel.getTitle());
                                try {
                                    //startActivity(whatsappIntent);
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
                                }




                            }

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });


                    }
                    //Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    //whatsappIntent.setType("text/plain");
                    //whatsappIntent.setPackage("com.whatsapp");
                    //whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Hey! let us go for " + eventFreeModel.getTitle());

                    //whatsappIntent.setType("image/png");

                    ///whatsappIntent.putExtra(Intent.EXTRA_STREAM, imageView.setImageBitmap(response.getBitmap()));


                    try {
                       // startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                       // Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
                    }


                }
            });*/



        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kenya_movie_theatre_details, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! let us go for " + eventFreeModel.getTitle()  + " at Kenya Theatres " + " Download Kenyamax on Google store and get more info\n https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax");
        sendIntent.setType("text/plain");
        miShareAction.setShareIntent(sendIntent);




        return true;

    }


    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        Log.d("range : ", "from: " + startDay + "-" + startMonth + "-" + startYear + " to : " + endDay + "-" + endMonth + "-" + endYear);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id ==android.R.id.home) {

            //NavUtils.navigateUpFromSameTask(this);
            finish();
            //onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
