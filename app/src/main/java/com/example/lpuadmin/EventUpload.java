package com.example.lpuadmin;

import com.google.firebase.database.Exclude;

public class EventUpload {

    private String mName;
    private String mDate;
    private String mImageUrl;
    private String mKey;
    private String mEventDesc;


    public EventUpload() {
        //empty constructor needed
    }

    public EventUpload(String name,  String date,String eventdesc, String imageUrl) {


        mName = name;
        mDate = date;
        mEventDesc = eventdesc;
        mImageUrl = imageUrl;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }


    public String getEventDesc() {
        return mEventDesc;
    }

    public void setEventDesc(String eventdesc) {

        mEventDesc = eventdesc;
    }


    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}

