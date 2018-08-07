package com.hanium.findplace.findplace_10.models;

import com.nhn.android.maps.maplib.NGeoPoint;

import java.util.Map;

public class CreatingAppModel {

    public String roomUid;
    public String appName;
    public MeetTime meetTime;
    public String category;
    public int participants_count;
    //<Users_Uid, NGeoPoint>
    public Map<String, NGeoPoint> user_completsLocationSelect;

    //constructor
    public CreatingAppModel(){

    }

    public static class MeetTime {

        //member variables
        public int year;
        public int month;
        public int day;
        public int hour;
        public int minute;

        //constructor
        public MeetTime(){

        }

    }

}
