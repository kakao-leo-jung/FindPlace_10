package com.hanium.findplace.findplace_10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SetLocationActivity extends AppCompatActivity {

    private TextView appName;
    private TextView appDate;
    private FrameLayout frameLayout;
    private EditText queryLocation;
    private Button searchLocation;
    private RecyclerView locationList;
    private TextView selectedLocation;
    private Button setMyLocation;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        String appUid = getIntent().getStringExtra("appUid");

        appName = (TextView) findViewById(R.id.SetLocation_TextView_appName);
        appName.setText(appUid);
        appDate = (TextView) findViewById(R.id.SetLocation_TextView_appDate);
        frameLayout = (FrameLayout) findViewById(R.id.SetLocation_FrameLayout_naverMapFragment);
        queryLocation = (EditText) findViewById(R.id.SetLocation_EditText_queryLocation);
        searchLocation = (Button) findViewById(R.id.SetLocation_Button_searchLocation);
        locationList = (RecyclerView) findViewById(R.id.SetLocation_RecyclerView_locationList);
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
}
