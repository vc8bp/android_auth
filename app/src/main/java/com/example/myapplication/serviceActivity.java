package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class serviceActivity extends AppCompatActivity {

    ImageView serviceImage;
    TextView serviceName;
    TextView serviceDescription;
    TextView servicePrice;
    TextView bookNowButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceImage = findViewById(R.id.service_image);
        serviceName = findViewById(R.id.service_title);
        serviceDescription = findViewById(R.id.service_description);
        servicePrice = findViewById(R.id.service_price);
        bookNowButton = findViewById(R.id.book_now_button);

        String serviceNameString = getIntent().getStringExtra("service");
        switch (serviceNameString) {
            case "spa":
                serviceImage.setImageResource(R.drawable.spa);
                serviceName.setText("Spa");
                serviceDescription.setText("Indulge in the ultimate relaxation experience at Serenity Spa. Our luxurious spa offers a tranquil and serene environment where you can unwind and rejuvenate your mind, body, and soul. From our soothing massages to our revitalizing body treatments, we use only the finest organic ingredients to help you achieve a state of total relaxation and bliss. Let our skilled and experienced therapists pamper you with our signature treatments and leave feeling refreshed, renewed, and revitalized. Visit us today and discover the true meaning of serenity.");
                servicePrice.setText("1000 RS");
                break;
            case "facial":
                serviceImage.setImageResource(R.drawable.facial);
                serviceName.setText("Facial");
                serviceDescription.setText("Our facial service is designed to provide a deep cleanse and nourishment to your skin. Our estheticians will analyze your skin and recommend a facial treatment that is tailored to your individual needs. We use high-quality products that are gentle on your skin and free from harmful chemicals. Our facial service includes a relaxing facial massage, steam treatment, and extractions (if needed) to leave your skin feeling refreshed and rejuvenated.");
                servicePrice.setText("500 Rs");
                break;
            case "makeup":
                serviceImage.setImageResource(R.drawable.makeup);
                serviceName.setText("Makeup");
                serviceDescription.setText("Our makeup service is perfect for special occasions or just a night out on the town. Our professional makeup artists will work with you to create the perfect look that accentuates your natural beauty. Whether you prefer a natural look or something more dramatic, we've got you covered. We use high-quality, long-lasting makeup products that will keep you looking flawless all night long.");
                servicePrice.setText("1500 Rs");
                break;
            case "hairstyle":
                serviceImage.setImageResource(R.drawable.hairstyle);
                serviceName.setText("Hairstyle");
                serviceDescription.setText("Our hairstyling service is designed to help you achieve the perfect look for any occasion. Whether you want a new haircut, color, or just a fresh blowout, our experienced hairstylists will work with you to create a style that complements your face shape and enhances your features. We use high-quality hair products that nourish and protect your hair, leaving it looking healthy and shiny. With our hairstyling service, you'll leave our salon feeling confident and ready to take on the day.");
                servicePrice.setText("2000 Rs");
                break;
            default:
                serviceImage.setImageResource(R.drawable.notfound);
                serviceName.setText("No Service Found");
                serviceDescription.setText("No Description Found with this Service");
                servicePrice.setText("0");
                break;
        }

        bookNowButton.setOnClickListener(v -> {

        });
    }
}
