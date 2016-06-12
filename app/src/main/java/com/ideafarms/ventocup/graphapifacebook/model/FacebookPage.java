package com.ideafarms.ventocup.graphapifacebook.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nick on 6/12/2016.
 */
public class FacebookPage implements Parcelable {

    private String message;
    private String created_time;
    private String picture;
    private String full_picture;
    private String id;

    public FacebookPage() {
    }

    protected FacebookPage(Parcel in) {
        message = in.readString();
        created_time = in.readString();
        picture = in.readString();
        full_picture = in.readString();
        id = in.readString();
    }

    public static final Creator<FacebookPage> CREATOR = new Creator<FacebookPage>() {
        @Override
        public FacebookPage createFromParcel(Parcel in) {
            return new FacebookPage(in);
        }

        @Override
        public FacebookPage[] newArray(int size) {
            return new FacebookPage[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(created_time);
        parcel.writeString(picture);
        parcel.writeString(full_picture);
        parcel.writeString(id);
    }
}
