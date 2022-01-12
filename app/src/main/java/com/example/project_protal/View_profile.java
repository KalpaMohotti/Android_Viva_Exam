package com.example.project_protal;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Map;


public class View_profile extends AppCompatActivity {
    Button ch,up;
    ImageView img;

    public Uri imguri;
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Intent i = getIntent();
        String email = i.getStringExtra("email");
        String admin="";
         admin=i.getStringExtra("admin");
        // String type=i.getStringExtra("type");

        filldata(email);






        ch = (Button) findViewById(R.id.button15);
        up = (Button) findViewById(R.id.button14);
        img = (ImageView) findViewById(R.id.imageView3);


        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });


        loadimages(email);

    }
//    private static final int TAKE_PICTURE = 1;
//    private Uri imageUri;
    public  void openCamera(View view){


        Intent i=new Intent("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(i);
    }



    public  String getdata(){
        String email="email";
        String password="password";
        String possition="possition";
        String a="";
        String b="";

        SharedPreferences s=getSharedPreferences("com.example.project_protal",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);//default name eka sahitha XML file eke data laba ganima..
        String value=s.getString(email,"not found");
        String value2=s.getString(password,"not found");
        String value3=s.getString(possition,"not found");



        Map<String,?> all=sharedPreferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()){
            // Toast toast=Toast.makeText(this,e.getKey()+" "+e.getValue(),Toast.LENGTH_SHORT);
            // toast.show();
            System.out.println(e.getKey()+" "+e.getValue());
            if(e.getKey().equals("possition") && e.getValue().equals("Admin")){
                a=e.getValue().toString();
            }else if(e.getKey().equals("possition") && e.getValue().equals("Student")){
                a=e.getValue().toString();
            }
            // a=e.getValue().toString();
        }

        return a;
    }





    public void loadimages(String email){


        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference photoReference= storageReference.child("images/"+email);

        final long ONE_MEGABYTE = 1024 * 1024;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                img.setImageBitmap(bmp);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
              //  Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Filechooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private void uploadImage() {
        TextView t=findViewById(R.id.textView30);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if(imguri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

           // StorageReference ref =storageReference.child("images/"+ UUID.randomUUID().toString());
            StorageReference ref =storageReference.child("images/"+ t.getText().toString());
            ref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(View_profile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(View_profile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }




    public  String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }

    public  void filldata(final String email){
        final TextView name=findViewById(R.id.textView26);
        final TextView batch=findViewById(R.id.textView25);
        final TextView emailtext=findViewById(R.id.textView30);
        final TextView type=findViewById(R.id.textView28);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference referenceuser=firebaseDatabase.getReference("User_details");

        ValueEventListener listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> childSnapshots = dataSnapshot.getChildren();
                for (DataSnapshot c : childSnapshots) {
                    User_details user = c.getValue(User_details.class);
                    System.out.println(user.getEmail());
                    System.out.println(user.getPassword());

                    if (user.getEmail().equals(email)) {

                        name.setText(user.getFname() + " " + user.getLname());
                        batch.setText(user.getYear());
                        emailtext.setText(user.getEmail());
                        type.setText(user.getPossition());


                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        };



        referenceuser.addListenerForSingleValueEvent(listener);
    }

}
