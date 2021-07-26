package com.example.lpuadmin;

import com.google.firebase.database.Exclude;

public class NewsUpload {

    private String mName;
    private String mDate;
    private String mImageUrl;
    private String mKey;
    private String mNewsDesc;

    public NewsUpload() {
        //empty constructor needed
    }

    public NewsUpload(String name,  String date,String newsdesc, String imageUrl) {


        mName = name;
        mDate = date;
        mNewsDesc = newsdesc;
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


    public String getNewsDesc() {
        return mNewsDesc;
    }

    public void setNewsDesc(String newsdesc) {

        mNewsDesc = newsdesc;
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





