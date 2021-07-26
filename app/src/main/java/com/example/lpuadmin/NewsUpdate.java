package com.example.lpuadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NewsUpdate extends AppCompatActivity {


    private Button mUpdateButton;

    private EditText updateDesc;
    private EditText updateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_update);


        mUpdateButton = findViewById(R.id.button_update);
        updateName = findViewById(R.id.newsupdate_title);
        updateDesc = findViewById(R.id.updatenews_desc);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //String updateImage = extras.getString("URL_TEXT");
        String newsName = extras.getString("NAME_TEXT2");
        String DescString = extras.getString("DESC_TEXT2");
        final String key = extras.getString("KEY2");

        updateName.setText(newsName);
        updateDesc.setText(DescString);


        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mupdateName = updateName.getText().toString();
                String mupdateDesc = updateDesc.getText().toString();


                UpdateItems(key, mupdateName, mupdateDesc);


            }
        });

    }


    private void UpdateItems(String Key, String name, String Desc) {


        final String NewsName = name;
        final String DescString = Desc;


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();

        mDatabaseRef.child("news").child(Key).child("name").setValue(NewsName);
        mDatabaseRef.child("news").child(Key).child("newsDesc").setValue(DescString);

        Toast.makeText(this, Key + NewsName + DescString, Toast.LENGTH_LONG).show();
    }
}

