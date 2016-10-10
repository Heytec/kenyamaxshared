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

//import static com.oensa.johnmuchiri.kenyamax.FragmentKenya.MOVIE_TRANSFER_KENYA;


public class KenyaDetailActivity extends ActionBarActivity {

    TextView movietitle,moviesynopsis ,movieyear,movierating,movievotes,imax,foxcinemaplexsarit,centuarycinemaxjuction,planetmediaprestigeplaxa,
    planetmediacinemawestgatemall,kenyacinema,angasky,nyalicinemax;
    Toolbar toolbar;
    ImageView imageView;
    ImageLoader imageLoader;
    RatingBar ratingBar;
    private VolleySingleton volleySingleton;
    private ShareActionProvider miShareAction;
    MovieKenya movieKenya;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kenya_detail);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //toolbar.getBackground().setAlpha(0);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent= getIntent();
        movieKenya=new MovieKenya();
        //movieKenya=intent.getParcelableExtra(MOVIE_TRANSFER_KENYA);


        movietitle =(TextView) findViewById(R.id.movieTitle);
        movietitle.setText(movieKenya.getTitle());


        movieyear =(TextView) findViewById(R.id.year);
        movieyear.setText(movieKenya.getYear());

        movierating=(TextView)findViewById(R.id.rating);
        movierating.setText(movieKenya.getImbdrating());


        movievotes=(TextView)findViewById(R.id.votes);
       movievotes.setText(movieKenya.getImbdvotes());





        moviesynopsis = (TextView) findViewById(R.id.synopsis);
        moviesynopsis.setText(movieKenya.getSynopsis());


        imax = (TextView)findViewById(R.id.imax);
        imax.setText(movieKenya.getImax());

        foxcinemaplexsarit=(TextView) findViewById(R.id.foxcinemaplexsarit);
        foxcinemaplexsarit.setText(movieKenya.getFoxCineplexSarit());


        centuarycinemaxjuction=(TextView)findViewById(R.id.CenturyCinemaxJuction);
        centuarycinemaxjuction.setText(movieKenya.getCenturyCinemaxJunction());


       planetmediaprestigeplaxa=(TextView)findViewById(R.id.Planetmediaprestigeplaza);
        planetmediaprestigeplaxa.setText(movieKenya.getPlanetMediaPrestigePlaza());


        planetmediacinemawestgatemall=(TextView)findViewById(R.id.Planetmediacinemawestgate);
       planetmediacinemawestgatemall.setText(movieKenya.getPlanetMediaCinemaWestgate());


       kenyacinema=(TextView)findViewById(R.id.Kenyacinema);
       kenyacinema.setText(movieKenya.getKenyacinema());

        angasky=(TextView)findViewById(R.id.AngaSky);
        angasky.setText(movieKenya.getAngasky());

        nyalicinemax=(TextView)findViewById(R.id.Planetmediacinemawestgate);
       nyalicinemax.setText(movieKenya.getNyalicinemax());









        //ratingBar=(RatingBar)findViewById(R.id.movieAudienceScore);
        //ratingBar.setRating(movie.getYear());



        imageView =(ImageView)findViewById(R.id.movieThumbnail);
        volleySingleton= VolleySingleton.getInstance();
        imageLoader=volleySingleton.getImageLoader();

        String urlThumnail=movieKenya.getPosters();
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
        getMenuInflater().inflate(R.menu.menu_kenya_detail, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch reference to the share action provider
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //sendIntent.putExtra(Intent.EXTRA_TEXT, "Now showing at Kenya  Movie  theatre\n" + movieKenya.getTitle() + "\nDownload Kenyamax at Google Play to latest events and movie ");
        sendIntent.putExtra(Intent.EXTRA_TEXT,"Hey join me as I  go to watch "+ movieKenya.getTitle() +". Download Kenyamax on Google Play Store to get more info\n https://play.google.com/store/apps/details?id=com.oensa.johnmuchiri.kenyamax");
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
        if (id == R.id.action_settings) {
            return true;
        }


        if (id ==android.R.id.home) {

            //NavUtils.navigateUpFromSameTask(this);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
