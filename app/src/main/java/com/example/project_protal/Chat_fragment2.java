package com.example.project_protal;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class Chat_fragment2 extends Fragment {
    ImageView img;
    String email;
    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view=inflater.inflate(R.layout.chat_fragment2,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle b) {
        String strtext = getArguments().getString("text");
        String date = getArguments().getString("date");
        email=getArguments().getString("email");
        System.out.println(strtext);

        TextView mTextView = view.findViewById(R.id.textView1);
        TextView mTextViewdate = view.findViewById(R.id.textDate);
        //  System.out.println(mTextView.getText());
        mTextViewdate.setText(date);
        mTextView.setText(strtext);
        img=view.findViewById(R.id.imageView1);
        image_read();

    }

    public void image_read(){




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

            }
        });
    }
    public  void getdata(){

        String name="email";


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String value = preferences.getString(name, "defaultValue");



        Map<String,?> all=preferences.getAll();  //map ekk thiyana value godak print kirima.
        for(Map.Entry e:all.entrySet()) {

            // String e=s.getString("name","not found");

            if(e.getKey().equals("email")){
                email=e.getValue().toString();
            }

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle b) {
        super.onSaveInstanceState(b);
        TextView textView= getView().findViewById(R.id.textView1);
        b.putString("txt1",textView.getText().toString());
    }
}
