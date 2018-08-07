package com.hanium.findplace.findplace_10.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.hanium.findplace.findplace_10.MakeAppointmentActivity;
import com.hanium.findplace.findplace_10.R;

public class MakeAppointment_dateFragment extends Fragment {

    private CalendarView calendarView;
    private Button before;
    private Button next;

    private MakeAppointmentActivity makeAppointmentActivity;

    public MakeAppointment_dateFragment(){

    }

    @SuppressLint("ValidFragment")
    public MakeAppointment_dateFragment(MakeAppointmentActivity parentApp){
        makeAppointmentActivity = parentApp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_appointment_date, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.MakeAppointment_date_calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                makeAppointmentActivity.myCalendar.setYear(year);
                makeAppointmentActivity.myCalendar.setMonth(month);
                makeAppointmentActivity.myCalendar.setDate(dayOfMonth);
            }
        });
        before = (Button) view.findViewById(R.id.MakeAppointment_date_Button_before);
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAppointmentActivity.viewPager.setCurrentItem(0);
            }
        });
        next = (Button) view.findViewById(R.id.MakeAppointment_date_Button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAppointmentActivity.viewPager.setCurrentItem(2);
            }
        });

        return view;
    }
}
