package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, pass;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    Button btn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPwd);
        btn = (Button) findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //login
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                perfAuth();
            }
        });

        //go to registeration
        TextView gotoRegister = (TextView) findViewById((R.id.lnkRegister));
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentt = new Intent(MainActivity.this,register.class);
                startActivity(intentt);
            }
        });


    }

    private void perfAuth() {
        String emails = email.getText().toString();
        String passs = pass.getText().toString();

        if(!emails.matches(emailRegex)){
            email.setError("Enter a correct email");
        } else {
            progressDialog.setMessage("Login....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(emails,passs).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(MainActivity.this,"Login successfull", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intentt = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intentt);
    }
}