package com.example.project_protal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;


public class emailVerifivation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verifivation);
       // final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
      //  Button b=findViewById(R.id.buttionSingup);
       // final TextView email=findViewById(R.id.textView);
      //  final TextView password=findViewById(R.id.textView31);
      //  b.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
//                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).
//                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(task.isSuccessful()){
//                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if(task.isSuccessful()){
//                                                Toast.makeText(emailVerifivation.this,"Registered Sucessfully",Toast.LENGTH_SHORT).show();
//                                            }else{
//                                                Toast.makeText(emailVerifivation.this,"Registration Fail",Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//                                    });
//
//
//                                }else{
//                                    Toast.makeText(emailVerifivation.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
    }
}
