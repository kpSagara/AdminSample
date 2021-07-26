package com.example.lpuadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventActivity extends AppCompatActivity implements EventAdapter.OnItemClickListener2 {

    Button upevent;
    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<EventUpload> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mProgressCircle = findViewById(R.id.progress_circle2);
        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mAdapter = new EventAdapter(EventActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // Set the layout manager to your recyclerview
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(EventActivity.this);

        mStorage = FirebaseStorage.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    EventUpload upload = postSnapshot.getValue(EventUpload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EventActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });

        upevent = (Button) findViewById(R.id.event_upload);
        upevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this, EventImageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onUpdateClick(int position) {
        Toast.makeText(this, "Update click at position: " + position, Toast.LENGTH_SHORT).show();

        EventUpload updateItem = mUploads.get(position);

        String url = updateItem.getImageUrl();
        String textName = updateItem.getName();
        String textDate = updateItem.getDate();
        String textDesc = updateItem.getEventDesc();
        final String selectedKey = updateItem.getKey();


        openUpdate(url, textName, textDate,textDesc, selectedKey);




    }

    public void openUpdate(String url, String textName, String textDate, String textDesc, String selectedKey)
    {

        String updateUrl = url;
        String updateName = textName;
        String updateDate = textDate;
        String updateDesc = textDesc;
        String updateKey = selectedKey;


        Intent intent = new Intent(EventActivity.this, UpdateItem.class);
        Bundle values = new Bundle();
        values.putString("URL_TEXT", updateUrl);
        values.putString("NAME_TEXT", updateName);
        values.putString("DATE_TEXT", updateDate);
        values.putString("DESC_TEXT", updateDesc);
        values.putString("KEY", updateKey);
        intent.putExtras(values);
        startActivity(intent);

    }

    @Override
    public void onDeleteClick(int position) {
        EventUpload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(EventActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}
