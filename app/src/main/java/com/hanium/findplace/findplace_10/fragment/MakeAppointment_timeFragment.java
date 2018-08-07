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
import android.widget.TimePicker;

import com.hanium.findplace.findplace_10.MakeAppointmentActivity;
import com.hanium.findplace.findplace_10.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MakeAppointment_timeFragment extends Fragment{

    //member variables
    private MakeAppointmentActivity makeAppointmentActivity;
    private MakeAppointment_checkFragment makeAppointment_checkFragment;

    private TimePicker timePicker;
    private Button before;
    private Button next;

    private Calendar seletedTime;

    //constructor
    public MakeAppointment_timeFragment(){

    }

    @SuppressLint("ValidFragment")
    public MakeAppointment_timeFragment(MakeAppointmentActivity parentApp, MakeAppointment_checkFragment checkApp){
        makeAppointmentActivity = parentApp;
        makeAppointment_checkFragment = checkApp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_appointment_time, container, false);

        timePicker = (TimePicker) view.findViewById(R.id.MakeAppointment_time_timePicker);
        before = (Button) view.findViewById(R.id.MakeAppointment_time_Button_before);
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAppointmentActivity.viewPager.setCurrentItem(1);
                makeAppointmentActivity.myCalendar.setHours(timePicker.getHour());
                makeAppointmentActivity.myCalendar.setMinutes(timePicker.getMinute());
            }
        });
        next = (Button) view.findViewById(R.id.MakeAppointment_time_Button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAppointmentActivity.myCalendar.setHours(timePicker.getHour());
                makeAppointmentActivity.myCalendar.setMinutes(timePicker.getMinute());

                SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd h:mm a");

                makeAppointment_checkFragment.textDate.setText(sdf.format(makeAppointmentActivity.myCalendar));
                makeAppointmentActivity.viewPager.setCurrentItem(3);
            }
        });

        return view;
    }
}
