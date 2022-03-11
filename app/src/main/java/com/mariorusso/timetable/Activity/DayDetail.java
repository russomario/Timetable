package com.mariorusso.timetable.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mariorusso.timetable.R;

public class DayDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_detail);


        Intent intent = getIntent();

        String dayNameTrans = intent.getStringExtra(MainActivity.DAY_NAME_TRANS);
        String dayNumbTrans = intent.getStringExtra(MainActivity.DAY_NUMBER_TRANS);
        String monthNameTrans = intent.getStringExtra(MainActivity.MONTH_NAME_TRANS);
        String layoutTrans = intent.getStringExtra(MainActivity.LAYOUT_TRANS);

        ViewCompat.setTransitionName(findViewById(R.id.day_name_detail), dayNameTrans);
        ViewCompat.setTransitionName(findViewById(R.id.day_number_detail), dayNumbTrans);
        ViewCompat.setTransitionName(findViewById(R.id.month_name_detail), monthNameTrans);
        ViewCompat.setTransitionName(findViewById(R.id.parent_layout_detail), layoutTrans);

        TextView dayNumber = findViewById(R.id.day_number_detail);
        dayNumber.setText(intent.getStringExtra(MainActivity.DAY_NUMBER_CONT));

        TextView dayName = findViewById(R.id.day_name_detail);
        dayName.setText(intent.getStringExtra(MainActivity.DAY_NAME_CONT));

        TextView monthName = findViewById(R.id.month_name_detail);
        monthName.setText(intent.getStringExtra(MainActivity.MONTH_NAME_CONT));

    }

    public void returnBack(View view) {
        onBackPressed();
    }
}
