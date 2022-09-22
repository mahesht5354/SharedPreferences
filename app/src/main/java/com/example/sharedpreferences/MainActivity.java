package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    EditText name, age;
    TextView result;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextTextPersonName2);
        age = findViewById(R.id.editTextNumber2);
        result = findViewById(R.id.textView);

        preferences = getSharedPreferences("NareshTech", MODE_PRIVATE);

    }

    //register the receiver
    @Override
    protected void onResume() {
        super.onResume();
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void loadData(View view) {

        String n = preferences.getString("NAME", "NO Data Found");
        int a = preferences.getInt("AGE", 0);

        result.setText(n +"\n" +a);


    }

    public void savaData(View view) {

        String n = name.getText().toString();
        int a = Integer.parseInt(age.getText().toString());

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("NAME", n);
        editor.putInt("AGE", a);

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        String n = preferences.getString("NAME", "NO Data Found");
        int a = preferences.getInt("AGE", 0);

        result.setText(n +"\n" +a);
    }
}