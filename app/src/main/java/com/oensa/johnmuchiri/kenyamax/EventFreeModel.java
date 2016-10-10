package com.oensa.johnmuchiri.kenyamax;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Pc on 9/18/2015.
 */
public class EventFreeModel  implements Parcelable {

    public static final Parcelable.Creator<EventFreeModel> CREATOR
            = new Parcelable.Creator<EventFreeModel>() {
        public EventFreeModel createFromParcel(Parcel in) {
            L.m("create from parcel :Movie");
            return new EventFreeModel(in);
        }

        public EventFreeModel[] newArray(int size) {
            return new EventFreeModel[size];
        }
    };

    private String Gigs;
    private  String id;
    private  String Title;
    private String Time;
    private  String  StartingDate;
    private  String EndingDate;
    private  String location;
    private  String Price;
    private  String Poster;
    private String Description;
    private String category;


    public EventFreeModel() {

    }



    public EventFreeModel(Parcel input) {

        Gigs =input.readString();
        id =input.readString();
        Title= input.readString();
        Time =input.readString();
        StartingDate =input.readString();
        EndingDate =input.readString();
        location =input.readString();
        Price =input.readString();
        Poster =input.readString();
        Description = input.readString();
        category = input.readString();

    }





    public EventFreeModel(String gigs,
                          String title,
                          String id,
                          String time,
                          String startingDate,
                          String endingDate,
                          String location,
                          String price,
                          String poster,
                          String description,
                          String category) {
        this.Gigs = gigs;
        this.Title = title;
        this.id = id;
        this.Time = time;
        this.StartingDate = startingDate;
        this.EndingDate = endingDate;
        this.location = location;
        this.Price = price;
        this.Poster = poster;
        this.Description = description;
        this.category = category;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGigs() {
        return Gigs;
    }

    public void setGigs(String gigs) {
        Gigs = gigs;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(String startingDate) {
        StartingDate = startingDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(String endingDate) {
        EndingDate = endingDate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "EventFreeModel{" +
                "Gigs='" + Gigs + '\'' +
                ", id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", Time='" + Time + '\'' +
                ", StartingDate='" + StartingDate + '\'' +
                ", EndingDate='" + EndingDate + '\'' +
                ", location='" + location + '\'' +
                ", Price='" + Price + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Description='" + Description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Gigs);
        dest.writeString(id);
        dest.writeString(Title);
        dest.writeString(Time);
        dest.writeString(StartingDate);
        dest.writeString(EndingDate);
        dest.writeString(location);
        dest.writeString(Price);
        dest.writeString(Poster);
        dest.writeString(Description);
        dest.writeString(category);

    }
}
