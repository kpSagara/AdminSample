package com.example.lpuadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class imagepopup extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepopup);

        TextView popname = findViewById(R.id.popname);
        ImageView popview = findViewById(R.id.imagepop);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String selectedItem = extras.getString("EXTRA_TEXT");
        final String popName = extras.getString("EXTRA_TEXT2");

        popname.setText(popName);


        Picasso.get().load(selectedItem)
                .placeholder(R.mipmap.lpuad)
                .fit()
                .centerInside()
                .into(popview);
    }
}
