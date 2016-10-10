package com.oensa.johnmuchiri.kenyamax;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Pc on 10/5/2015.
 */
public class MovieTheatreModel   implements Parcelable{

    public static final Creator<MovieTheatreModel> CREATOR
            = new Creator<MovieTheatreModel>() {
        public MovieTheatreModel createFromParcel(Parcel in) {
            //L.m("create from parcel :Movie");
            return new MovieTheatreModel(in);
        }

        public MovieTheatreModel[] newArray(int size) {
            return new MovieTheatreModel[size];
        }
    };


    private String title;
  private String year;
  private String genre;
  private String synopsis;
  private String actors;
  private String imdbrarating;
  private String imbdvotes;
  private String poster;
  private String timeshowing;
  private String monday;
  private String tuesday;
  private String wednesday;
  private String thursday;
  private String friday;
  private String satarday;
  private String sunday;



    public MovieTheatreModel(){

    }


    public MovieTheatreModel(Parcel input) {
        title=input.readString();
        year=input.readString();
        genre=input.readString();
        synopsis=input.readString();
        actors=input.readString();
        imdbrarating=input.readString();
        imbdvotes=input.readString();
        poster=input.readString();
        timeshowing=input.readString();
        monday=input.readString();
        tuesday=input.readString();
        wednesday=input.readString();
        thursday=input.readString();
        friday=input.readString();
        satarday=input.readString();
        sunday=input.readString();

    }



    public MovieTheatreModel(String title, String year, String genre, String synopsis, String actors, String imdbrarating, String imbdvotes, String poster, String timeshowing, String monday, String tuesday, String wednesday, String thursday, String friday, String satarday, String sunday) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.synopsis = synopsis;
        this.actors = actors;
        this.imdbrarating = imdbrarating;
        this.imbdvotes = imbdvotes;
        this.poster = poster;
        this.timeshowing = timeshowing;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.satarday = satarday;
        this.sunday = sunday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbrarating() {
        return imdbrarating;
    }

    public void setImdbrarating(String imdbrarating) {
        this.imdbrarating = imdbrarating;
    }

    public String getImbdvotes() {
        return imbdvotes;
    }

    public void setImbdvotes(String imbdvotes) {
        this.imbdvotes = imbdvotes;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTimeshowing() {
        return timeshowing;
    }

    public void setTimeshowing(String timeshowing) {
        this.timeshowing = timeshowing;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSatarday() {
        return satarday;
    }

    public void setSatarday(String satarday) {
        this.satarday = satarday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }


    @Override
    public String toString() {
        return "MovieTheatreModel{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbrarating='" + imdbrarating + '\'' +
                ", imbdvotes='" + imbdvotes + '\'' +
                ", poster='" + poster + '\'' +
                ", timeshowing='" + timeshowing + '\'' +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", satarday='" + satarday + '\'' +
                ", sunday='" + sunday + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(genre);
        dest.writeString(synopsis);
        dest.writeString(actors);
        dest.writeString(imdbrarating);
        dest.writeString(imbdvotes);
        dest.writeString(poster);
        dest.writeString(timeshowing);
        dest.writeString(monday);
        dest.writeString(tuesday);
        dest.writeString(wednesday);
        dest.writeString(thursday);
        dest.writeString(friday);
        dest.writeString(satarday);
        dest.writeString(sunday);


    }
}
