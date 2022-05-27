package com.excample.recipeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        ImageView img = findViewById(R.id.imageViewz);
        img.setImageResource(R.drawable.food3);

    }

    public void Clicktomain(View view){

        EditText em = findViewById(R.id.email);
        String email = em.getText().toString();
        EditText ps = findViewById(R.id.password);
        String password = ps.getText().toString();
        if(!email.equals("")&&!password.equals("")){
            this.loginuser(email,password);
        }
        else if(email.equals("")){
            Toast.makeText(LoginActivity.this, "กรุณากรอกอีเมลล์", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(LoginActivity.this, "กรุณากรอกรหัสผ่าน", Toast.LENGTH_LONG).show();
        }
    }

    public void loginuser(String em, String ps){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth.signInWithEmailAndPassword(em, ps).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_LONG).show();
                    String users[] = em.split("@");
                    intent.putExtra("email",users[0]);
                    startActivity(intent);
                    finish();
                }else{
                    Log.e("test", "login failed");
                    Toast.makeText(LoginActivity.this, "กรุณากรอกอีเมล์หรือรหัสผ่านให้ถูกต้อง", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void OnclickRegister(View view){
//          startActivity(RegisterActivity.class);
//          finish();
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }




}