package com.example.lpuadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final int PICK_IMAGE_REQUEST = 1;

    private String currentDateString;

    private Button mUpdateButton;

    private EditText updateDesc;
    private EditText updateName;
    private TextView updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        Button datebutton = (Button) findViewById(R.id.datebutton);
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });



        mUpdateButton = findViewById(R.id.button_update);
         updateName = findViewById(R.id.edit_text_file_update);
         ImageView showImage = findViewById(R.id.image_view);
         updateDate = findViewById(R.id.datetextupdate);
         updateDesc = findViewById(R.id.event_update);



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
         String updateImage = extras.getString("URL_TEXT");
         String eventName = extras.getString("NAME_TEXT");
         String DateString = extras.getString("DATE_TEXT");
         String DescString = extras.getString("DESC_TEXT");
         final String key = extras.getString("KEY");

        updateName.setText(eventName);
        updateDate.setText(DateString);
        updateDesc.setText(DescString);


        Picasso.get().load(updateImage)
                .placeholder(R.mipmap.lpuad)
                .fit()
                .centerInside()
                .into(showImage);





        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mupdateName = updateName.getText().toString();
                String mupdateDate = updateDate.getText().toString();
                String mupdateDesc = updateDesc.getText().toString();



                UpdateItems(key ,mupdateName, mupdateDate, mupdateDesc);


            }
        });

    }



    private void UpdateItems(String Key,String name, String date, String Desc) {


        final String eventName = name;
        final String DateString = date;
        final String DescString = Desc;


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();

        mDatabaseRef.child("events").child(Key).child("name").setValue(eventName);
        mDatabaseRef.child("events").child(Key).child("date").setValue(DateString);
        mDatabaseRef.child("events").child(Key).child("eventDesc").setValue(DescString);

        Toast.makeText(this, Key + eventName + DateString + DescString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textview = (TextView) findViewById(R.id.datetextupdate);
        textview.setText(currentDateString);


    }
}
