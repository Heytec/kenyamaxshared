package com.oensa.johnmuchiri.kenyamax;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Pc on 8/26/2015.
 */
public class MEventModel implements Parcelable {

    public static final Creator<MEventModel> CREATOR
            = new Creator<MEventModel>() {
        public MEventModel createFromParcel(Parcel in) {
           // L.m("create from parcel :Movie");
            return new MEventModel(in);
        }

        public MEventModel[] newArray(int size) {
            return new MEventModel[size];
        }
    };


    private String id;
    private String event;
    private String venue;
    private String date ;
    private String time;
    private String price;
    private String poster;
    private String Events;






    public MEventModel(Parcel input) {

        id =input.readString();
        event =input.readString();
        venue =input.readString();
        date=input.readString();
        time =input.readString();
        price =input.readString();
        poster =input.readString();
        Events =input.readString();

    }

    public MEventModel() {

    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getEvents() {
        return Events;
    }

    public void setEvents(String events) {
        Events = events;
    }

    @Override
    public String toString() {
        return "MEventModel{" +
                "id='" + id + '\'' +
                ", event='" + event + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                ", poster='" + poster + '\'' +
                ", Events='" + Events + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

     dest.writeString(id);
     dest.writeString(event);
     dest.writeString(venue);
     dest.writeString(date);
     dest.writeString(time);
     dest.writeString(price);
     dest.writeString(poster);
     dest.writeString(Events);






    }
}
