package com.mariorusso.timetable.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mariorusso.timetable.Adapters.RecyclerView;
import com.mariorusso.timetable.R;
import com.mariorusso.timetable.Utility.calendar_utility;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MARIO";
    public static final String DAY_NAME_TRANS = "day_name_trans";
    public static final String DAY_NUMBER_TRANS = "day_number_tans";
    public static final String MONTH_NAME_TRANS = "day_number_trans";
    public static final String LAYOUT_TRANS = "layout_trans";
    public static final String DAY_NAME_CONT = "day_name_cont";
    public static final String DAY_NUMBER_CONT = "day_number_cont";
    public static final String MONTH_NAME_CONT = "month_name_cont";

    private int[] week;
    private String[] monthsName;

    private DrawerLayout drawer;
    private NavigationView navView;

    private ActivityOptions options;
    private Intent sharedIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        initRecyclerView();
        initDrawer();

        navView =  findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.home);


    }

    @Override
    protected void onResume() {
        navView.setCheckedItem(R.id.home);
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.week_days:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(this, DaysSelection.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    void initDrawer(){
        drawer = findViewById(R.id.drawer_layout);
        drawer.setScrimColor(getColor(R.color.blurWhite));

        // Expand margin of the trigger area of the drawer
        try {

            Field mDragger = drawer.getClass().getDeclaredField("mLeftDragger");
            mDragger.setAccessible(true);
            ViewDragHelper draggerObj = (ViewDragHelper) mDragger.get(drawer);

            Field mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");
            mEdgeSize.setAccessible(true);
            int edge = mEdgeSize.getInt(draggerObj);

            mEdgeSize.setInt(draggerObj, edge * 2);

        } catch (NoSuchFieldException e){
            Log.e(TAG, "onCreate: NoSuchFieldException. " + e.getMessage());
        } catch (IllegalAccessException e){
            Log.e(TAG, "onCreate: IllegalAccessException. " + e.getMessage());
        }
    }

    void initVariables(){
        SparseArray[] map = calendar_utility.getCurrentWeek();
        week = (int[]) map[0].get(0);
        monthsName = (String[]) map[1].get(0);
    }

    private void initRecyclerView(){
        android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView adapter = new RecyclerView(week, monthsName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void openDrawer(View view) {
        if (!drawer.isDrawerOpen(GravityCompat.START))
            drawer.openDrawer(GravityCompat.START);
    }

    @SuppressWarnings("unchecked")
    public void viewDay(View view){

        int id = view.getId();
        sharedIntent = new Intent(MainActivity.this, DayDetail.class);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<>(findViewById(id-4), ViewCompat.getTransitionName(findViewById(id-4)));
        pairs[1] = new Pair<>(findViewById(id-3), ViewCompat.getTransitionName(findViewById(id-3)));
        pairs[2] = new Pair<>(findViewById(id-2), ViewCompat.getTransitionName(findViewById(id-2)));
        pairs[3] = new Pair<>(findViewById(id-1), ViewCompat.getTransitionName(findViewById(id-1)));

        sharedIntent.putExtra(DAY_NAME_TRANS, ViewCompat.getTransitionName(findViewById(id-4)));
        sharedIntent.putExtra(DAY_NUMBER_TRANS, ViewCompat.getTransitionName(findViewById(id-3)));
        sharedIntent.putExtra(MONTH_NAME_TRANS, ViewCompat.getTransitionName(findViewById(id-2)));
        sharedIntent.putExtra(LAYOUT_TRANS, ViewCompat.getTransitionName(findViewById(id-1)));

        TextView textTemp = findViewById(id-4);
        sharedIntent.putExtra(DAY_NAME_CONT, textTemp.getText().toString());

        textTemp = findViewById(id-3);
        sharedIntent.putExtra(DAY_NUMBER_CONT, textTemp.getText().toString());

        textTemp = findViewById(id-2);
        sharedIntent.putExtra(MONTH_NAME_CONT, textTemp.getText().toString());


        options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
        startActivity(sharedIntent, options.toBundle());
    }
}
