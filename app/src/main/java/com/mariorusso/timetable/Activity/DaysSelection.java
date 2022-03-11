package com.mariorusso.timetable.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mariorusso.timetable.R;

public class DaysSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_selection);

        Toast.makeText(this, "Week days", Toast.LENGTH_LONG).show();
    }
}
