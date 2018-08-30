package com.example.adammb.pelemzamannow;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelem implements Parcelable {
    private int id;
    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;
    private double vote_average;
    private int vote_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.poster_path);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
    }

    public Pelem() {
    }

    protected Pelem(Parcel in) {
        this.id = in.readInt();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.poster_path = in.readString();
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
    }

    public static final Parcelable.Creator<Pelem> CREATOR = new Parcelable.Creator<Pelem>() {
        @Override
        public Pelem createFromParcel(Parcel source) {
            return new Pelem(source);
        }

        @Override
        public Pelem[] newArray(int size) {
            return new Pelem[size];
        }
    };
}
