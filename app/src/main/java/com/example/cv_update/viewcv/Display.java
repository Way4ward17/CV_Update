package com.example.cv_update.viewcv;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cv_update.MainActivity;
import com.example.cv_update.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Display extends AppCompatActivity {

    private ArtistsAdapter adapter;
    private RecyclerView recyclerView;
    StorageReference storageReference;
    DatabaseReference reference;
    private List<ArtistBlog> artistList;
    private SwipeRefreshLayout swipe;
    TextView back;
    DatabaseReference dbArtists;
private String holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        reference = FirebaseDatabase.getInstance().getReference();
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipeVerify);
        swipe.setRefreshing(true);
        readAll();
        back = (TextView)(findViewById(R.id.backDisplay));
        back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Display.this,
                MainActivity.class);
        //  intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
});

        if(getIntent().getExtras() != null) {
            holder = getIntent().getExtras().getString("cat_Name");

        }else{

        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //populate recyclerview


      Query query3 = FirebaseDatabase.getInstance().getReference("Post")
                .orderByChild("category")
                .equalTo(holder);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                readAll();

                Query query3 = FirebaseDatabase.getInstance().getReference("Post")
                        .orderByChild("category")
                        .equalTo(holder);
                query3.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        //dbArtists = FirebaseDatabase.getInstance().getReference().child("Post");
     query3.addListenerForSingleValueEvent(valueEventListener);





        }



    @Override
    protected void onStart() {
        super.onStart();
        //OFFLINE
        //initialize the variables

    }

    private void readAll(){

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList = new ArrayList<>();
        adapter = new ArtistsAdapter(this, artistList);
        recyclerView.setAdapter(adapter);



    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ArtistBlog artist = snapshot.getValue(ArtistBlog.class);
                    artistList.add(artist);
                }
                swipe.setRefreshing(false);
                adapter.notifyDataSetChanged();

            }else{
                swipe.setRefreshing(false);

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };




}