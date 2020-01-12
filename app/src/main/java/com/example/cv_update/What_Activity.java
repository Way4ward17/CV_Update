package com.example.cv_update;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class What_Activity extends AppCompatActivity {
    private EditText fullname1, phone1;
    private EditText email1, password1;
    private Button submitt;

    DatabaseReference reference;
    FirebaseUser fuser;
    TextView back;
    FirebaseAuth auth;
    long maxid;
    ProgressBar progressBar;
    CheckBox check;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_);
        reference = FirebaseDatabase.getInstance().getReference();
        fullname1 = (EditText) (findViewById(R.id.fullname));
        phone1 = (EditText) (findViewById(R.id.phone));
        submitt = (Button) (findViewById(R.id.loginWhat));
        email1 = (EditText) findViewById(R.id.email);
        password1 = (EditText) findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.loader);



        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = email1.getText().toString();
                String txt_desc = password1.getText().toString();
                String txt_address = fullname1.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_desc) || TextUtils.isEmpty(txt_address)) {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "All field are required", Snackbar.LENGTH_LONG).show();
                } else {
                        submit();
                }
            }
        });
    }

    private void submit(){
        progressBar.setVisibility(View.VISIBLE);
                            final String txt_username = fullname1.getText().toString();
                            final String txt_phone = phone1.getText().toString();
                            final String email = email1.getText().toString();
                            final String txt_password = password1.getText().toString();
                            auth.createUserWithEmailAndPassword(email, txt_password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                                assert firebaseUser != null;
                                                String userid = firebaseUser.getUid();
                                                HashMap<String, Object> hashMap = new HashMap<>();
                                                hashMap.put("fullname", txt_username);
                                                hashMap.put("phone", txt_phone);
                                                hashMap.put("email", email);
                                                hashMap.put("id", String.valueOf(maxid + 1));

                                                reference.child(userid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                          //  progressDialog.hide();
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            View parentLayout = findViewById(android.R.id.content);
                                                            Snackbar.make(parentLayout, "Registration Success", Snackbar.LENGTH_SHORT).show();

                                                            Intent main = new Intent(What_Activity.this, ActivityLogin.class);
                                                            startActivity(main);
                                                            finish();

                                                        }
                                                    }
                                                });
                                            } else if (!task.isSuccessful()) {

                                                View parentLayout = findViewById(android.R.id.content);
                                                Snackbar.make(parentLayout, task.getException() + "", Snackbar.LENGTH_SHORT).show();


                                            }


                                        }






                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(What_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        }

    private boolean network(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }

        return false;
    }
    }





