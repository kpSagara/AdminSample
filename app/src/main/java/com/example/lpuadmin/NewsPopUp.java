package com.example.lpuadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsPopUp extends AppCompatActivity {

    private ImageView popview;
    private TextView popname;
    private TextView popdate;
    private TextView popdesc;
    private String newsName;
    private String newsDate;
    private String newsDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pop_up);


        popview = findViewById(R.id.imagepop);

        popname = findViewById(R.id.newstitle);
        popdate = findViewById(R.id.newsdate);
        popdesc = findViewById(R.id.newsdesc);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String selectedItem = extras.getString("EXTRA_IMAGE");
        newsName = extras.getString("EXTRA_NAME");
        newsDate = extras.getString("EXTRA_DATE");
        newsDesc = extras.getString("EXTRA_DESC");

        popname.setText(newsName);
        popdate.setText(newsDate);
        popdesc.setText(newsDesc);


        Picasso.get().load(selectedItem)
                .placeholder(R.mipmap.lpuad)
                .fit()
                .centerInside()
                .into(popview);
    }
}