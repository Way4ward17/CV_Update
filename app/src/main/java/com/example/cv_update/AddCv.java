package com.example.cv_update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

public class AddCv extends AppCompatActivity {
private EditText fullname, address, phone1, phone2, email, nationalty, stateoforigin, dob
        ,discipline, education, otherqualification,workingexperience,personalquality,language,ref;
FirebaseAuth auth;
Spinner gender;
Button button;
ProgressBar progressBar;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;


//upload image
StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
ImageView imageView;
    int PLACE_PICKER_REQUEST = 1;
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
        auth = FirebaseAuth.getInstance();
        nationalty = (EditText)findViewById(R.id.nationaltycv);
        stateoforigin =(EditText)findViewById(R.id.stateoforigincv);
        dob = (EditText)findViewById(R.id.dateofbirthcv);
        button = (Button) (findViewById(R.id.buttonup));
        discipline = (EditText)findViewById(R.id.disciplinecv);
        education = (EditText)findViewById(R.id.educationcv);
        otherqualification = (EditText)findViewById(R.id.otherqualificationcv);
        workingexperience = (EditText)findViewById(R.id.workingexperiencecv);
        personalquality = (EditText)findViewById(R.id.personalqualitiescv);
        language = (EditText)findViewById(R.id.languagespokencv);
        ref = (EditText)findViewById(R.id.refereescv);
        gender = (Spinner)findViewById(R.id.gendercv);
        progressBar = (ProgressBar)(findViewById(R.id.loader));

        imageView = (ImageView)(findViewById(R.id.profileImage));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
    }


    private void openImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropResultSize(512, 512)
                .setAspectRatio(1, 1)
                .setAutoZoomEnabled(true)
                .setAllowFlipping(true)
                .setAllowRotation(true)
                .start(AddCv.this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                imageView.setImageURI(imageUri);

                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(AddCv.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    //     uploadImage();
                }
            }
        }


    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = this.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void savedata(){
progressBar.setVisibility(View.VISIBLE);
        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;
        String userid = firebaseUser.getUid();
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
        reference.child(userid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "CV Uploaded Successfully", Snackbar.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, e + "Error", Snackbar.LENGTH_SHORT).show();

            }
        });

    }







    private void uploadImage() {

        if (!network()) {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Please turn ON your network", Snackbar.LENGTH_INDEFINITE).show();
        } else {


            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Uploading Details");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            if (imageUri != null) {
                final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                        + "." + getFileExtension(imageUri));

                uploadTask = fileReference.putFile(imageUri);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return fileReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

savedata();
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCv.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
            } else {
                pd.hide();
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "No image selected", Snackbar.LENGTH_LONG).show();
            }
        }
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
