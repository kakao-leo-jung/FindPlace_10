package com.hanium.findplace.findplace_10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.hanium.findplace.findplace_10.fragment.MakeAppointment_categoryFragment;
import com.hanium.findplace.findplace_10.fragment.MakeAppointment_checkFragment;
import com.hanium.findplace.findplace_10.fragment.MakeAppointment_dateFragment;
import com.hanium.findplace.findplace_10.fragment.MakeAppointment_timeFragment;

import java.util.Date;

public class MakeAppointmentActivity extends AppCompatActivity {

    //member variables
    public final String MYTAG = "MyLog_____________";

    public ViewPager viewPager;

    public int app_category;
    public String app_name;
    public Date myCalendar;
    public String chatRoomUid;

    private MakeAppointment_categoryFragment makeAppointment_categoryFragment;
    private MakeAppointment_dateFragment makeAppointment_dateFragment;
    private MakeAppointment_timeFragment makeAppointment_timeFragment;
    private MakeAppointment_checkFragment makeAppointment_checkFragment;

    //setting Category--------------------------------------------------------------
    public final String[] CATEGORY = new String[]{
            "Category", "카페", "식당", "스터디룸"
    };
    public final int CATEGORY_NULL = 0;
    public final int CATEGORY_CAFE = 1;
    public final int CATEGORY_RESTAURANT = 2;
    public final int CATEGORY_STUDYROOM = 3;
    //setting Category--------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        chatRoomUid = getIntent().getStringExtra("roomUid");

        myCalendar = new Date();
        app_name = "";

        makeAppointment_categoryFragment = new MakeAppointment_categoryFragment(this);
        makeAppointment_dateFragment = new MakeAppointment_dateFragment(this);
        makeAppointment_checkFragment = new MakeAppointment_checkFragment(this, app_category, myCalendar);
        makeAppointment_timeFragment = new MakeAppointment_timeFragment(this, makeAppointment_checkFragment);

        viewPager = (ViewPager) findViewById(R.id.MakeAppointment_viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        final GestureDetector gd = new GestureDetector(new GestureDetector.SimpleOnGestureListener());

        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE:

                        gd.onTouchEvent(event);

                        break;
                }
                return true;
            }
        });


    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            if (position == 0) {
                return makeAppointment_categoryFragment;
            } else if (position == 1) {
                return makeAppointment_dateFragment;
            } else if (position == 2) {
                return makeAppointment_timeFragment;
            } else {
                return makeAppointment_checkFragment;
            }

        }

        @Override
        public int getCount() {
            return 4;  // 총 3개의 page를 보여줍니다.
        }

    }

}

