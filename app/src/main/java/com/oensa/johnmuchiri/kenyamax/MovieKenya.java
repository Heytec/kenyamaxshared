package com.oensa.johnmuchiri.kenyamax;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Pc on 9/10/2015.
 */
public class MovieKenya implements Parcelable {

    public static final Creator<MovieKenya> CREATOR
            = new Creator<MovieKenya>() {
        public MovieKenya createFromParcel(Parcel in) {
            //L.m("create from parcel :Movie");
            return new MovieKenya(in);
        }

        public MovieKenya[] newArray(int size) {
            return new MovieKenya[size];
        }
    };




    private String title;
    private String year;
    private String genre;
    private String actors;
    private String synopsis;
    private String imbdrating;
    private String imbdvotes;
    private String posters;
    private String theatreshowing;
    private String imax;
    private  String FoxCineplexSarit;
    private String CenturyCinemaxJunction;
    private  String PlanetMediaPrestigePlaza;
    private  String PlanetMediaCinemaWestgate;
    private  String Kenyacinema;
    private String Angasky;
    private String Nyalicinemax;


    public MovieKenya(){

    }


    public MovieKenya(Parcel input) {
        title=input.readString();
        year=input.readString();
        genre=input.readString();
        actors=input.readString();
        synopsis=input.readString();
        imbdvotes=input.readString();
        imbdrating=input.readString();
        posters=input.readString();
        theatreshowing=input.readString();
        imax=input.readString();
        FoxCineplexSarit=input.readString();
        CenturyCinemaxJunction=input.readString();
        PlanetMediaPrestigePlaza=input.readString();
        PlanetMediaCinemaWestgate=input.readString();
        Kenyacinema=input.readString();
        Angasky=input.readString();
        Nyalicinemax=input.readString();
    }






    public MovieKenya(String nyalicinemax, String angasky, String kenyacinema, String planetMediaCinemaWestgate, String planetMediaPrestigePlaza, String centuryCinemaxJunction, String foxCineplexSarit, String theatreshowing, String imax, String posters, String imbdvotes, String imbdrating, String synopsis, String actors, String genre, String year, String title) {
        this.Nyalicinemax = nyalicinemax;
        this.Angasky = angasky;
        this.Kenyacinema = kenyacinema;
        this.PlanetMediaCinemaWestgate = planetMediaCinemaWestgate;
        this.PlanetMediaPrestigePlaza = planetMediaPrestigePlaza;
        this.CenturyCinemaxJunction = centuryCinemaxJunction;
        this.FoxCineplexSarit = foxCineplexSarit;
        this.theatreshowing = theatreshowing;
        this.imax = imax;
        this.posters = posters;
        this.imbdvotes = imbdvotes;
        this.imbdrating = imbdrating;
        this.synopsis = synopsis;
        this.actors = actors;
        this.genre = genre;
        this.year = year;
        this.title = title;
    }


    public String getNyalicinemax() {
        return Nyalicinemax;
    }

    public void setNyalicinemax(String nyalicinemax) {
        Nyalicinemax = nyalicinemax;
    }

    public String getAngasky() {
        return Angasky;
    }

    public void setAngasky(String angasky) {
        Angasky = angasky;
    }

    public String getKenyacinema() {
        return Kenyacinema;
    }

    public void setKenyacinema(String kenyacinema) {
        Kenyacinema = kenyacinema;
    }

    public String getPlanetMediaCinemaWestgate() {
        return PlanetMediaCinemaWestgate;
    }

    public void setPlanetMediaCinemaWestgate(String planetMediaCinemaWestgate) {
        PlanetMediaCinemaWestgate = planetMediaCinemaWestgate;
    }

    public String getCenturyCinemaxJunction() {
        return CenturyCinemaxJunction;
    }

    public void setCenturyCinemaxJunction(String centuryCinemaxJunction) {
        CenturyCinemaxJunction = centuryCinemaxJunction;
    }

    public String getFoxCineplexSarit() {
        return FoxCineplexSarit;
    }

    public void setFoxCineplexSarit(String foxCineplexSarit) {
        FoxCineplexSarit = foxCineplexSarit;
    }

    public String getImbdvotes() {
        return imbdvotes;
    }

    public void setImbdvotes(String imbdvotes) {
        this.imbdvotes = imbdvotes;
    }

    public String getImbdrating() {
        return imbdrating;
    }

    public void setImbdrating(String imbdrating) {
        this.imbdrating = imbdrating;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImax() {
        return imax;
    }

    public void setImax(String imax) {
        this.imax = imax;
    }

    public String getPosters() {
        return posters;
    }

    public void setPosters(String posters) {
        this.posters = posters;
    }

    public String getTheatreshowing() {
        return theatreshowing;
    }

    public void setTheatreshowing(String theatreshowing) {
        this.theatreshowing = theatreshowing;
    }

    public String getPlanetMediaPrestigePlaza() {
        return PlanetMediaPrestigePlaza;
    }

    public void setPlanetMediaPrestigePlaza(String planetMediaPrestigePlaza) {
        PlanetMediaPrestigePlaza = planetMediaPrestigePlaza;
    }


    @Override
    public String toString() {
        return "MovieKenya{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", actors='" + actors + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", imbdrating='" + imbdrating + '\'' +
                ", imbdvotes='" + imbdvotes + '\'' +
                ", posters='" + posters + '\'' +
                ", theatreshowing='" + theatreshowing + '\'' +
                ", imax='" + imax + '\'' +
                ", FoxCineplexSarit='" + FoxCineplexSarit + '\'' +
                ", CenturyCinemaxJunction='" + CenturyCinemaxJunction + '\'' +
                ", PlanetMediaPrestigePlaza='" + PlanetMediaPrestigePlaza + '\'' +
                ", PlanetMediaCinemaWestgate='" + PlanetMediaCinemaWestgate + '\'' +
                ", Kenyacinema='" + Kenyacinema + '\'' +
                ", Angasky='" + Angasky + '\'' +
                ", Nyalicinemax='" + Nyalicinemax + '\'' +
                '}';
    }



    @Override
    public int describeContents() {
//        L.m("describe Contents Movie");
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        L.m("writeToParcel Movie");
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(genre);
        dest.writeString(actors);
        dest.writeString(synopsis);
        dest.writeString(imbdvotes);
        dest.writeString(imbdrating);
        dest.writeString(posters);
        dest.writeString(theatreshowing);
        dest.writeString(imax);
        dest.writeString(FoxCineplexSarit);
        dest.writeString(CenturyCinemaxJunction);
        dest.writeString(PlanetMediaPrestigePlaza);
        dest.writeString(PlanetMediaCinemaWestgate);
        dest.writeString(Kenyacinema);
        dest.writeString(Angasky);
        dest.writeString(Nyalicinemax);
    }

}
