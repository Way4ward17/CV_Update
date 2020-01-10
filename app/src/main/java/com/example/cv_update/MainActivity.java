package com.example.cv_update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cv_update.viewcv.ArtistBlog;

public class MainActivity extends AppCompatActivity {
ArtistBlog artistList;
private TextView fullname, address, email, phone1, phone2, nationalty, stateoforigin, dateofbirth, sex, discipline,
    education, otherqualification, workingexperience, personalquality, languagespoken, interestso, refereeso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullname = (TextView)findViewById(R.id.fullnameso);
        address = (TextView)findViewById(R.id.addressso);
        email = (TextView)findViewById(R.id.emailso);
        phone1 = (TextView)findViewById(R.id.phoneoneso);
        phone2 = (TextView)findViewById(R.id.phone2so);
        nationalty = (TextView)findViewById(R.id.nationaltyso);
        stateoforigin = (TextView)findViewById(R.id.stateoforiginso);
        dateofbirth = (TextView)findViewById(R.id.dateofbirthso);
        sex = (TextView)findViewById(R.id.sexso);
        discipline = (TextView)findViewById(R.id.disiplineso);

        education = (TextView)findViewById(R.id.educationso);
        otherqualification = (TextView)findViewById(R.id.otherqualificationso);
        workingexperience = (TextView)findViewById(R.id.workingexperienceso);
        personalquality = (TextView)findViewById(R.id.personalqualitiesso);
        languagespoken = (TextView)findViewById(R.id.languagespokenso);
        interestso = (TextView)findViewById(R.id.interestso);
        refereeso = (TextView)findViewById(R.id.refereeso);



        artistList = new ArtistBlog();
        fullname.setText(getIntent().getExtras().getString("fullname"));
        address.setText(getIntent().getExtras().getString("address"));
        email.setText(getIntent().getExtras().getString("email"));
        phone1.setText(getIntent().getExtras().getString("fullname"));
        phone2.setText(getIntent().getExtras().getString("phone2"));

        nationalty.setText(getIntent().getExtras().getString("nationalty"));
        stateoforigin.setText(getIntent().getExtras().getString("stateoforigin"));
        dateofbirth.setText(getIntent().getExtras().getString("dob"));
        sex.setText(getIntent().getExtras().getString("sex"));
        discipline.setText(getIntent().getExtras().getString("discipline"));

        education.setText(getIntent().getExtras().getString("education"));
        otherqualification.setText(getIntent().getExtras().getString("otherqualification"));
        workingexperience.setText(getIntent().getExtras().getString("workingexperience"));
        personalquality.setText(getIntent().getExtras().getString("personalquality"));
        languagespoken.setText(getIntent().getExtras().getString("language"));
        interestso.setText(getIntent().getExtras().getString("ref"));
        refereeso.setText(getIntent().getExtras().getString("gender"));
    }
}
