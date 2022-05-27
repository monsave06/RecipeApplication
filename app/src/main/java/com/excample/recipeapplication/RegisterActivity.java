package com.excample.recipeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
     EditText mName ,mEmail,mPassword1,mPassword2 ;
     Button mRegister ;
     FirebaseAuth mAuth;
     ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.editTextName);
        mEmail = findViewById(R.id.editTextEmail);
        mPassword1 = findViewById(R.id.editTextPassword);
        mPassword2 = findViewById(R.id.editTextPassword2);
        mRegister = findViewById(R.id.buttonRe);

        mAuth = FirebaseAuth.getInstance();
       progressBar = findViewById(R.id.progressBar);

//        if(mAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
////            finish();
//        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword1.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Error Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword1.setError("Error password");
                    return;
                }
                if (password.length() < 4) {
                    mPassword1.setError(" password >= 4");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,"userCreact",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
       });
    }
}
