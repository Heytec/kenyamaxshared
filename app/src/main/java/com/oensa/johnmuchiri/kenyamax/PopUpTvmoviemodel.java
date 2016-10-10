package com.oensa.johnmuchiri.kenyamax;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Pc on 10/20/2015.
 */
public class PopUpTvmoviemodel   implements Parcelable {


    public static final Creator<PopUpTvmoviemodel> CREATOR
            = new Creator<PopUpTvmoviemodel>() {
        public PopUpTvmoviemodel createFromParcel(Parcel in) {
            //L.m("create from parcel :Movie");
            return new PopUpTvmoviemodel(in);
        }

        public PopUpTvmoviemodel[] newArray(int size) {
            return new PopUpTvmoviemodel[size];
        }
    };




   public String title;
    public  String synopsis;
    public String releasedate;
    public String poster;
    public String popularity;
    public String rating;
    public String totalvotes;



    public PopUpTvmoviemodel(){

    }


    public PopUpTvmoviemodel(Parcel input) {
       title=input.readString();
       synopsis=input.readString();
        releasedate=input.readString();
        poster=input.readString();
        popularity=input.readString();
        rating=input.readString();
        totalvotes=input.readString();

    }



    public PopUpTvmoviemodel(String synopsis, String releasedate, String title, String poster, String popularity, String rating, String totalvotes) {
        this.synopsis = synopsis;
        this.releasedate = releasedate;
        this.title = title;
        this.poster = poster;
        this.popularity = popularity;
        this.rating = rating;
        this.totalvotes = totalvotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotalvotes() {
        return totalvotes;
    }

    public void setTotalvotes(String totalvotes) {
        this.totalvotes = totalvotes;
    }


    @Override
    public String toString() {
        return "PopUpTvmoviemodel{" +
                "title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", releasedate='" + releasedate + '\'' +
                ", poster='" + poster + '\'' +
                ", popularity='" + popularity + '\'' +
                ", rating='" + rating + '\'' +
                ", totalvotes='" + totalvotes + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(synopsis);
        dest.writeString(releasedate);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(totalvotes);

    }
}
