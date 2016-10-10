package com.oensa.johnmuchiri.kenyamax;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import static com.oensa.johnmuchiri.kenyamax.FragmentTvshows.EVENT_TRANSFER_KENYA;


public class SeriesDeatilActivity extends ActionBarActivity {

    TextView movietitle,moviesynopsis ,movieyear,movierating,movievotes,synopsis;
    TextView descrion  , startingdate ,endingdate;
    Toolbar toolbar;
    ImageView imageView;
    ImageLoader imageLoader;
    RatingBar ratingBar;
    private VolleySingleton volleySingleton;
    private ShareActionProvider miShareAction;
    PopUpTvmoviemodel eventFreeModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_deatil);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //toolbar.getBackground().setAlpha(0);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        Intent intent = getIntent();
        eventFreeModel = new PopUpTvmoviemodel();
        eventFreeModel =  intent.getParcelableExtra(FragmentFree.EVENT_TRANSFER_KENYA);


        movietitle = (TextView) findViewById(R.id.movieTitle);
        movietitle.setText(eventFreeModel.getTitle());


        movieyear = (TextView) findViewById(R.id.year);
        movieyear.setText(eventFreeModel.getPopularity());

        movierating = (TextView) findViewById(R.id.rating);
        movierating.setText(eventFreeModel.getRating());


        movievotes = (TextView) findViewById(R.id.votes);
        movievotes.setText(eventFreeModel.getTotalvotes());

        descrion =(TextView)findViewById(R.id.description);
        descrion.setText(eventFreeModel.getSynopsis());


        //moviesynopsis = (TextView) findViewById(R.id.synopsis);
        //moviesynopsis.setText(eventFreeModel.getLocation());


        startingdate =(TextView)findViewById(R.id.startingdatedetails);
        startingdate.setText(eventFreeModel.releasedate);

        //endingdate=(TextView)findViewById(R.id.endingdtedetails);
        //endingdate.setText(eventFreeModel.getEndingDate());



        //ratingBar=(RatingBar)findViewById(R.id.movieAudienceScore);
        //ratingBar.setRating(movie.getYear());


        imageView = (ImageView) findViewById(R.id.movieThumbnail);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();

        String urlThumnail = eventFreeModel.getPoster();
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


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_series_deatil, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Am reviewing " + eventFreeModel.getTitle()  + " Download Kenyamax on Google store and get more info\n https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax");
        sendIntent.setType("text/plain");
        miShareAction.setShareIntent(sendIntent);


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
            //onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
