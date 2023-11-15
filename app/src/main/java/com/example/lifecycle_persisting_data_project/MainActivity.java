package com.example.lifecycle_persisting_data_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText editText1, editText2;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSpinner = findViewById(R.id.shapeSpinner);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.shapes_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void calculateResult() {
        String selectedShape = shapeSpinner.getSelectedItem().toString();
        double value1 = parseEditText(editText1);
        double value2 = parseEditText(editText2);

        double result = 0;

        // Perform calculation based on the selected shape
        switch (selectedShape) {
            case "Triangle":
                result = 0.5 * value1 * value2;
                break;
            case "Square":
                result = value1 * value1;
                break;
            case "Rectangle":
                result = value1 * value2;
                break;
        }

        resultTextView.setText("Result: " + result);
    }

    private double parseEditText(EditText editText) {
        try {
            return Double.parseDouble(editText.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}