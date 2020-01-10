package com.example.cv_update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCv extends AppCompatActivity {
private EditText fullname, address, phone1, phone2, email, nationalty, stateoforigin, dob
        ,discipline, education, otherqualification,workingexperience,personalquality,language,ref;

Spinner gender;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cv);
        reference = FirebaseDatabase.getInstance().getReference("Data");
        fullname = (EditText)findViewById(R.id.fullnamecv);
        address = (EditText)findViewById(R.id.addresscv);
        phone1 = (EditText)findViewById(R.id.phone1cv);
        phone2 =(EditText)findViewById(R.id.phone2cv);
        email = (EditText)findViewById(R.id.emailcv);
        nationalty = (EditText)findViewById(R.id.nationaltycv);
        stateoforigin =(EditText)findViewById(R.id.stateoforigincv);
        dob = (EditText)findViewById(R.id.dateofbirthcv);
        discipline = (EditText)findViewById(R.id.disciplinecv);
        education = (EditText)findViewById(R.id.educationcv);
        otherqualification = (EditText)findViewById(R.id.otherqualificationcv);
        workingexperience = (EditText)findViewById(R.id.workingexperiencecv);
        personalquality = (EditText)findViewById(R.id.personalqualitiescv);
        language = (EditText)findViewById(R.id.languagespokencv);
        ref = (EditText)findViewById(R.id.refereescv);
        gender = (Spinner)findViewById(R.id.gendercv);
    }




    private void savedata(){

        HashMap<Object, String> map = new HashMap<>();
        map.put("fullname", fullname.getText().toString());
        map.put("address", address.getText().toString());
        map.put("phone1", phone1.getText().toString());
        map.put("phone2", phone2.getText().toString());
        map.put("email", email.getText().toString());
        map.put("nationalty", nationalty.getText().toString());
        map.put("stateoforigin", stateoforigin.getText().toString());
        map.put("dob", dob.getText().toString());
        map.put("discipline", discipline.getText().toString());
        map.put("education", education.getText().toString());
        map.put("otherqualification", otherqualification.getText().toString());
        map.put("workingexperience", workingexperience.getText().toString());
        map.put("personalquality", personalquality.getText().toString());
        map.put("language", language.getText().toString());
        map.put("ref", ref.getText().toString());
        map.put("gender", gender.getSelectedItem().toString());
        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, task.getException() + "", Snackbar.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, e + "", Snackbar.LENGTH_SHORT).show();

            }
        });

    }
}
