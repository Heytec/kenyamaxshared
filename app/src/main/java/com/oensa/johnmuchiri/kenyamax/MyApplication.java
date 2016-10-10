package com.oensa.johnmuchiri.kenyamax;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by John Pc on 8/3/2015.
 */
public class MyApplication extends Application {
    public static final String API_KEY_ROTTEN_TOMATOES="9v3ykzhh497xarunpmen5ehk";
    public static final String API_KEY_KENYA_THEATRE="850b17f83631daf62ec8ff3e7214b753";
    private static MyApplication sInstance;
    private Tracker mTracker;



    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;





    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        Parse.initialize(this, "y02LAgKvJdYdJIji1YtFLrJKJen5j4smLhuvaFVp", "St0oiUFHwvSwND4g5A3XoMJG2cOerTDAGMOpflf1");

        ParseInstallation.getCurrentInstallation().saveInBackground();




        mInstance = this;

        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);







        Map<String, String> dimensions = new HashMap<String, String>();
// What type of news is this?
        dimensions.put("category", "Events");
// Is it a weekday or the weekend?
        dimensions.put("dayType", "weekday");
// Send the dimensions to Parse along with the 'read' event

        ParseAnalytics.trackEventInBackground("read", dimensions);



    }


    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
           //mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }




    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }











    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    /***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     */
    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();

        // Set screen name.
        t.setScreenName(screenName);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /***
     * Tracking exception
     *
     * @param e exception to be tracked
     */
    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                            .setDescription(
                                    new StandardExceptionParser(this, null)
                                            .getDescription(Thread.currentThread().getName(), e))
                            .setFatal(false)
                            .build()
            );
        }
    }

    /***
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     */
    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }














}
