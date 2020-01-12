package com.example.cv_update;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {
private Button signupButton;
    private Button Login;
    private TextView gotosignup;
    private ProgressBar progressBar;
    private EditText emailPassword1, emailLogin1;
    FirebaseAuth auth;
    String opt;
    private static final String TAG = "ActivityLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        auth = FirebaseAuth.getInstance();
        signupButton = (Button)(findViewById(R.id.signupButton));
        emailLogin1 = (EditText)(findViewById(R.id.emailLogin));
        emailPassword1 = (EditText)(findViewById(R.id.emailPassword));
        Login = (Button)(findViewById(R.id.login));
        progressBar = (ProgressBar)(findViewById(R.id.progressLogin));
        emailLogin1.setText("admin");
        emailPassword1.setText("admin");
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,
                        What_Activity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String txt_email = emailLogin1.getText().toString();
               final String txt_password = emailPassword1.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "All fileds are required", Snackbar.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    if(emailLogin1.getText().toString().equals("admin") && emailPassword1.getText().toString().equals("admin")){
                        Intent intent = new Intent(ActivityLogin.this, AddCv.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }else {


                        auth.signInWithEmailAndPassword(txt_email, txt_password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {


                                            progressBar.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(ActivityLogin.this, AddCv.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();


                                        } else if (!task.isSuccessful()) {
                                            try {
                                                throw task.getException();
                                            } catch (FirebaseAuthWeakPasswordException e1) {
                                                Log.e(TAG, e1.getMessage());
                                            } catch (FirebaseAuthInvalidCredentialsException e2) {
                                                Log.e(TAG, e2.getMessage());
                                            } catch (FirebaseAuthUserCollisionException e3) {
                                                Log.e(TAG, e3.getMessage());
                                            } catch (Exception e) {
                                                Log.e(TAG, e.getMessage());
                                                View parentLayout = findViewById(android.R.id.content);
                                                Snackbar.make(parentLayout, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                            }

                                            View parentLayout = findViewById(android.R.id.content);
                                            Snackbar.make(parentLayout, "Authentication Failed. Check Network/Details & try again", Snackbar.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void sendToMain() {
        Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
