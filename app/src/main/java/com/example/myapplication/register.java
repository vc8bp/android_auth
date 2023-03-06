package com.example.myapplication;

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

public class register extends AppCompatActivity {

    EditText name, email, pass;
    TextView loginLink;
    String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    Button btn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name =  findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPwd);
        btn = findViewById(R.id.btnRegister);
        loginLink = findViewById(R.id.lnkLogin);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        loginLink.setOnClickListener(v -> sendUserToNextActivity());
        btn.setOnClickListener(v -> perfAuth());
    }

    private void perfAuth() {
//        String names = name.getText().toString();
        String emails = email.getText().toString();
        String passs = pass.getText().toString();

        if(!emails.matches(emailRegex)){
            email.setError("Enter a correct email");
        } else if(passs.isEmpty() || passs.length() < 6){
            pass.setError("password length should be minimum 6 charector's");
        } else {
            progressDialog.setMessage("Registrating....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(emails,passs).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(register.this,"Registeration successfull", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(register.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void sendUserToNextActivity() {
        Intent intentt = new Intent(register.this,MainActivity.class);
        startActivity(intentt);
    }
}