package com.avb.collageproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edit_text_emailaddress;
    EditText edit_text_password;
    Button button_login;
    String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser uAuth;

    TextView scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text_emailaddress = findViewById(R.id.emailInputLogin);
        edit_text_password = findViewById(R.id.passwordInputLogin);
        button_login = findViewById(R.id.button_login);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        uAuth = mAuth.getCurrentUser();

        if(uAuth != null) {
            // If user session exists, navigate to dashboard activity
            sendUserToNextActivity();
        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfromLogin();

            }
        });
    }

    private void perfromLogin() {
        String email = edit_text_emailaddress.getText().toString();
        String password = edit_text_password.getText().toString();

        if (!email.matches(emailPattern)){
            edit_text_emailaddress.setError("Enter Correct Email");
        }else if(password.isEmpty() || password.length()<6){
            edit_text_password.setError("Input  proper password..");
        }else{
            progressDialog.setMessage("Please Wait While Login.....");
            progressDialog.setTitle("Regestration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this, "Login is sucecessful", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this, Admin_Dashboard_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
