package com.hanium.findplace.findplace_10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hanium.findplace.findplace_10.models.CreatingAppModel;
import com.hanium.findplace.findplace_10.naver_api.LocationAsyncCallback;
import com.hanium.findplace.findplace_10.naver_api.SearchLocation;
import com.hanium.findplace.findplace_10.naver_api.SetMyLocation;

import java.util.List;

public class SetLocationActivity extends AppCompatActivity {

    private CreatingAppModel thisCreatingAppModel;

    private TextView appName;
    private TextView appDate;
    private FrameLayout frameLayout;
    private EditText queryLocation;
    private Button searchLocation;
    private RecyclerView locationList_recyclerView;
    private TextView selectedLocation;
    private Button setMyLocation;
    private Button cancel;

    private List<SetMyLocation> setMyLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        String appUid = getIntent().getStringExtra("appUid");
        String roomUid = getIntent().getStringExtra("roomUid");
        FirebaseDatabase.getInstance().getReference().child("MakingAppointments").child(roomUid).child(appUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                thisCreatingAppModel = dataSnapshot.getValue(CreatingAppModel.class);
                appName.setText(thisCreatingAppModel.appName);
                appDate.setText(thisCreatingAppModel.meetTime.year+"/"+thisCreatingAppModel.meetTime.month+"/"+thisCreatingAppModel.meetTime.day+" "
                        +thisCreatingAppModel.meetTime.hour+":"+thisCreatingAppModel.meetTime.minute);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        appName = (TextView) findViewById(R.id.SetLocation_TextView_appName);
        appDate = (TextView) findViewById(R.id.SetLocation_TextView_appDate);

        //지도 설정해야함
        frameLayout = (FrameLayout) findViewById(R.id.SetLocation_FrameLayout_naverMapFragment);


        queryLocation = (EditText) findViewById(R.id.SetLocation_EditText_queryLocation);
        searchLocation = (Button) findViewById(R.id.SetLocation_Button_searchLocation);
        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchLocation searchLocation = new SearchLocation(queryLocation.getText().toString(), new LocationAsyncCallback() {
                    @Override
                    public void getLocationList(List<SetMyLocation> list) {

                        locationList_recyclerView.setLayoutManager(new LinearLayoutManager(SetLocationActivity.this));
                        locationList_recyclerView.setAdapter(new MySetLocationListAdapter(list));

                    }
                });
                searchLocation.execute();

            }
        });

        locationList_recyclerView = (RecyclerView) findViewById(R.id.SetLocation_RecyclerView_locationList);
        selectedLocation = (TextView) findViewById(R.id.SetLocation_TextView_selectedLocation);

        setMyLocation = (Button) findViewById(R.id.SetLocation_Button_setMyLocation);
        setMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //여기는 DB에 업데이트 해줘야함.
                //일단 나는 내 위치를 설정했다는 알림을 DB에 업데이트하고 내 시작위치정보를 저장.



            }
        });


        cancel = (Button) findViewById(R.id.SetLocation_Button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class MySetLocationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<SetMyLocation> list;

        //constructor
        public MySetLocationListAdapter(List<SetMyLocation> list){
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_set_location, parent, false);

            return MySetLocationListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class MySetLocationListViewHolder extends RecyclerView.ViewHolder {

        //constructor
        public MySetLocationListViewHolder(View itemView) {
            super(itemView);
        }
    }

}
