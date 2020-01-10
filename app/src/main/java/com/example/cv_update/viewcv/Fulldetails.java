package com.example.cv_update.viewcv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cv_update.R;

public class Fulldetails extends AppCompatActivity {
ArtistBlog artistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulldetails);
        artistList = new ArtistBlog();
    }
}
