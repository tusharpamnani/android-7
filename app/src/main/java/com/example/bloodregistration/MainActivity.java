package com.example.bloodregistration;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField, bloodGroupField, cityField;
    private Button addButton, fetchButton;
    private TextView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.usernameField);
        bloodGroupField = findViewById(R.id.bloodGroupField);
        cityField = findViewById(R.id.cityField);
        addButton = findViewById(R.id.addButton);
        fetchButton = findViewById(R.id.fetchButton);
        displayData = findViewById(R.id.displayData);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameField.getText().toString();
                String bloodGroup = bloodGroupField.getText().toString();
                String city = cityField.getText().toString();

                if (!username.isEmpty() && !bloodGroup.isEmpty() && !city.isEmpty()) {
                    // Storing data in SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("USERNAME", username);
                    editor.putString("BLOOD_GROUP", bloodGroup);
                    editor.putString("CITY", city);
                    editor.apply(); // Save the data

                    Toast.makeText(MainActivity.this, "Data added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE);

                // Fetching data from SharedPreferences
                String fetchedUsername = sharedPreferences.getString("USERNAME", "No user found");
                String fetchedBloodGroup = sharedPreferences.getString("BLOOD_GROUP", "N/A");
                String fetchedCity = sharedPreferences.getString("CITY", "N/A");

                // Display the fetched data
                if (!fetchedUsername.equals("No user found")) {
                    displayData.setText("Username: " + fetchedUsername + "\nBlood Group: " + fetchedBloodGroup + "\nCity: " + fetchedCity);
                } else {
                    displayData.setText("No user found!");
                }
            }
        });
    }
}
