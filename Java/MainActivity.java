package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String selectedGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText Age, height_ft, height_in, weight;
        TextView result;
        Button male_botton, female_botton, calcu_botton;

        Age = findViewById(R.id.Age);
        height_ft = findViewById(R.id.height);
        height_in = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        male_botton = findViewById(R.id.male_botton);
        female_botton = findViewById(R.id.female_botton);
        calcu_botton = findViewById(R.id.calcu_botton);

        // Male Button Click
        male_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Male";
                Toast.makeText(MainActivity.this, "Gender: Male Selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Female Button Click
        female_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Female";
                Toast.makeText(MainActivity.this, "Gender: Female Selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Calculate Button Click
        calcu_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedGender.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int ft = Integer.parseInt(height_ft.getText().toString());
                    int inch = Integer.parseInt(height_in.getText().toString());
                    int wt = Integer.parseInt(weight.getText().toString());

                    int totalInch = ft * 12 + inch;
                    double totalCm = totalInch * 2.54;
                    double totalMeter = totalCm / 100;
                    double bmi = wt / (totalMeter * totalMeter);

                    String category;
                    if (bmi < 18.5) {
                        category = "Underweight";
                    } else if (bmi >= 18.5 && bmi < 24.9) {
                        category = "Normal weight";
                    } else if (bmi >= 25 && bmi < 29.9) {
                        category = "Overweight";
                    } else {
                        category = "Obese";
                    }

                    result.setText(String.format("Gender: %s\nBMI: %.2f\nCategory: %s", selectedGender, bmi, category));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
