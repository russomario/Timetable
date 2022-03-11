package com.mariorusso.timetable.Utility;

import static com.mariorusso.timetable.Activity.MainActivity.TAG;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseArray;

import com.mariorusso.timetable.Activity.MainActivity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class calendar_utility {

    public static String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private final static int MONTH_SHORT = 0;
    private final static int MONTH_LONG = 1;

     private static String getCurrentMonth(int format, Calendar calendar){
        if (format == MONTH_SHORT)
            return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        else if (format == MONTH_LONG)
            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        else
            throw new IllegalArgumentException("The format of the month must be: MONTH_SHORT or MONTH_LONG");
    }

    public static String getDay(int count) {
         Calendar calendar = Calendar.getInstance();
         int today = calendar.get(Calendar.DAY_OF_WEEK);
         return daysOfWeek[(count + today - 2 + 7) % 7] ; // -2 perché MONDAY corrisponde alla costante 2, aggiungo 7 in modo da non avere numeri negativi
    }

    @SuppressLint("DefaultLocale")
    public static SparseArray[] getCurrentWeek() {
         int[] dayNumbers = new int[7];
         String[] monthName = new String[7];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));
        for (int i = 0; i < dayNumbers.length; i++) {
            dayNumbers[i] = calendar.get(Calendar.DAY_OF_MONTH);
            monthName[i] = String.format("%s%d", getCurrentMonth(MONTH_SHORT, calendar), i);
            calendar.add(Calendar.DATE, 1);
        }
        Log.w(TAG, Arrays.toString(dayNumbers));
        SparseArray<int[]> days = new SparseArray<>();
        SparseArray<String[]> months = new SparseArray<>();
        days.append(0, dayNumbers);
        months.append(0, monthName);
        SparseArray[] map = new SparseArray[2];
        map[0] = days;
        map[1] = months;
        return map;
    }


}
