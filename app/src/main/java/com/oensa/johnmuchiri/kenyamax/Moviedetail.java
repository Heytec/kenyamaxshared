package com.oensa.johnmuchiri.kenyamax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;




public class Moviedetail extends ActionBarActivity {

    TextView movietitle,moviesynopsis ,moviedate;
    Toolbar toolbar;
    ImageView imageView;
    ImageLoader imageLoader;
    RatingBar ratingBar;
    private VolleySingleton volleySingleton;
    private ShareActionProvider miShareAction;
    Movie movie;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);



        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent= getIntent();
        //movie=(Movie)intent.getParcelableExtra(MOVIE_TRANSFER);

        movietitle =(TextView) findViewById(R.id.movieTitle);
         movietitle.setText(movie.getTitle());


       moviesynopsis=(TextView) findViewById(R.id.textbox);
       moviesynopsis.setText(movie.getSynopsis());


        moviedate =(TextView) findViewById(R.id.movieReleaseDate);
      moviedate.setText(movie.getReleaseDateTheater().toString());


        ratingBar=(RatingBar)findViewById(R.id.movieAudienceScore);
            ratingBar.setRating(movie.getAudienceScore());



       imageView =(ImageView)findViewById(R.id.movieThumbnail);
        volleySingleton= VolleySingleton.getInstance();
        imageLoader=volleySingleton.getImageLoader();

        String urlThumnail=movie.getUrlThumbnail();
        if(urlThumnail!=null) {
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
        getMenuInflater().inflate(R.menu.menu_moviedetail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch reference to the share action provider
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //sendIntent.putExtra(Intent.EXTRA_TEXT, movie.getTitle()+"This is my text to send.");
        sendIntent.putExtra(Intent.EXTRA_TEXT,"Hey join me as I  go to watch "+ movie.getTitle() +". Download Kenyamax on Google Play Store to get more info\n https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax");
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

        //noinspection SimplifiableIfStatement


        if (id ==android.R.id.home) {

            //NavUtils.navigateUpFromSameTask(this);
            finish();

            return true;
        }





        return super.onOptionsItemSelected(item);
    }
}
