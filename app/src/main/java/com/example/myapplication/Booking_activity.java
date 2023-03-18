package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class Booking_activity extends AppCompatActivity {

    EditText nameEditText, numberEditText, dateEditText;
    TextView serviceLable;
    Button bookNowButton;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        nameEditText = findViewById(R.id.name_input);
        numberEditText = findViewById(R.id.number_input);
        dateEditText = findViewById(R.id.date_input);
        serviceLable = findViewById(R.id.service_label);
        bookNowButton = findViewById(R.id.submit_button);
        progressDialog = new ProgressDialog(this);

        String service = getIntent().getStringExtra("service");
        serviceLable.setText(service);
        setTitle("Book Now - " + service);

        bookNowButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String number = numberEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();

            if (name.isEmpty()) {
                nameEditText.setError("Please enter your name");
                nameEditText.requestFocus();
                return;
            }
            if (number.isEmpty()) {
                numberEditText.setError("Please enter your phone number");
                numberEditText.requestFocus();
                return;
            }
            if (date.isEmpty()) {
                dateEditText.setError("Please enter booking date");
                dateEditText.requestFocus();
                return;
            }

            progressDialog.setMessage("Booking....");
            progressDialog.setTitle("Booking");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference().child("bookings").child("hemloo");
            BookingClass bookingclass = new BookingClass(name, number, date, service);
            FirebaseDatabase.getInstance().getReference("bookings").child(name).setValue(bookingclass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(this , "Booking successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(this, "Booking failed, please try again", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Log.e("Firebase", "Error while saving booking: " + e.getMessage());
                Toast.makeText(this, "Booking failed, please try again", Toast.LENGTH_SHORT).show();
            });
        });
    }


}

