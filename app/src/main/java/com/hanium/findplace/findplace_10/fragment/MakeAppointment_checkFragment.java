package com.hanium.findplace.findplace_10.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hanium.findplace.findplace_10.MakeAppointmentActivity;
import com.hanium.findplace.findplace_10.R;
import com.hanium.findplace.findplace_10.models.CreatingAppModel;

import java.util.Date;

public class MakeAppointment_checkFragment extends Fragment {

    //member variables
    private MakeAppointmentActivity makeAppointmentActivity;

    private int categoryStatus;
    private Date calendar;

    private TextView appName;
    private TextView categoryName;
    public TextView textDate;
    private Button before;
    private Button createAppointment;
    private Button cancel;

    //constructor
    public MakeAppointment_checkFragment(){

    }

    @SuppressLint("ValidFragment")
    public MakeAppointment_checkFragment(MakeAppointmentActivity parentApp, int category, Date calendar){

        makeAppointmentActivity = parentApp;
        categoryStatus = category;
        this.calendar = calendar;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_appointment_check, container, false);

        appName = (TextView) view.findViewById(R.id.MakeAppointment_check_TextView_appName);
        appName.setText(makeAppointmentActivity.app_name);
        categoryName = (TextView) view.findViewById(R.id.MakeAppointment_check_TextView_category);
        categoryName.setText(makeAppointmentActivity.CATEGORY[makeAppointmentActivity.app_category]);
        textDate = (TextView) view.findViewById(R.id.MakeAppointment_check_TextView_time);
        before = (Button) view.findViewById(R.id.MakeAppointment_check_Button_before);
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAppointmentActivity.viewPager.setCurrentItem(2);
            }
        });
        createAppointment = (Button) view.findViewById(R.id.MakeAppointment_check_Button_create);
        createAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //여기서 약속 생성 DB 코드 짜야함!!!!!!!!!!


                //디비를 어떻게 보내지?
                //새 약속

                //생성중이라는 로딩창을 띄운다

                //파이어베이스에 업데이트를 한다
                final CreatingAppModel creatingAppModel = new CreatingAppModel();
                final CreatingAppModel.MeetTime meetTime = new CreatingAppModel.MeetTime();
                meetTime.year = calendar.getYear();
                meetTime.month = calendar.getMonth();
                meetTime.day = calendar.getDate();
                meetTime.hour = calendar.getHours();
                meetTime.minute = calendar.getMinutes();

                creatingAppModel.roomUid = makeAppointmentActivity.chatRoomUid;
                creatingAppModel.appName = makeAppointmentActivity.app_name;
                creatingAppModel.meetTime = meetTime;
                creatingAppModel.category = makeAppointmentActivity.CATEGORY[makeAppointmentActivity.app_category];
                FirebaseDatabase.getInstance().getReference().child("ChatRooms").child(makeAppointmentActivity.chatRoomUid).child("users_uid").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            i++;
                        }
                        Log.d("MYLOG____________", "이 대화방에 속한 유저수 : "+i);
                        creatingAppModel.participants_count = i;

                        //DB에 새로운 MakingAppointments 푸시
                        FirebaseDatabase.getInstance().getReference().child("MakingAppointments").child(makeAppointmentActivity.chatRoomUid).push().setValue(creatingAppModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {


                                //여기에 모임방 속한 인원 모두에게 푸시메세지를 보내야함.





                                getActivity().finish();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });
        cancel = (Button) view.findViewById(R.id.MakeAppointment_check_Button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        return view;
    }

}
