package com.mariorusso.timetable.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mariorusso.timetable.R;
import com.mariorusso.timetable.Utility.calendar_utility;


public class RecyclerView extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int[] dayNumbers;
    private final String[] monthName;
    private int cont=0;

    public RecyclerView(int[] dayNumbers, String[] monthName) {
        this.dayNumbers = dayNumbers;
        this.monthName = monthName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_activity_main, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.dayName.setText(calendar_utility.getDay(i));
        viewHolder.dayName.setId(cont++);

        viewHolder.dayNumber.setText(String.valueOf(dayNumbers[i]));
        viewHolder.dayNumber.setId(cont++);

        viewHolder.monthName.setText(monthName[i].substring(0, monthName[i].length() - 1));
        viewHolder.monthName.setId(cont++);

        viewHolder.parentLayout.setId(cont++);

        viewHolder.btnExpand.setId(cont++);

        ViewCompat.setTransitionName(viewHolder.dayNumber, String.valueOf(viewHolder.dayNumber.getId()));
        ViewCompat.setTransitionName(viewHolder.dayName, String.valueOf(viewHolder.dayName.getId()));
        ViewCompat.setTransitionName(viewHolder.monthName, String.valueOf(viewHolder.monthName.getId()));
        ViewCompat.setTransitionName(viewHolder.parentLayout, String.valueOf(viewHolder.parentLayout.getId()));


    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

        TextView dayNumber;
        TextView dayName;
        TextView monthName;
        FrameLayout btnExpand;
        LinearLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);

            dayName = itemView.findViewById(R.id.day_name);
            dayNumber = itemView.findViewById(R.id.day_number);
            monthName = itemView.findViewById(R.id.month_name);
            btnExpand = itemView.findViewById(R.id.btn_expand);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
