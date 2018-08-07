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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hanium.findplace.findplace_10.MakeAppointmentActivity;
import com.hanium.findplace.findplace_10.R;

public class MakeAppointment_categoryFragment extends Fragment {

    private final String MYTAG = "MyLog____________";



    private Spinner materialBetterSpinner;
    private EditText app_name_editText;

    public int categoryStatus;

    private Button next;

    MakeAppointmentActivity makeAppointmentActivity;

    public MakeAppointment_categoryFragment() {

    }

    @SuppressLint("ValidFragment")
    public MakeAppointment_categoryFragment(MakeAppointmentActivity parentApp){
        makeAppointmentActivity = parentApp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_appointment_category, container, false);

        materialBetterSpinner = (Spinner) view.findViewById(R.id.MakeAppointment_category_BetterSpinner);
        if(makeAppointmentActivity.app_name != null){
        }

        app_name_editText = (EditText) view.findViewById(R.id.MakeAppointment_category_EditText_appName);

        next = (Button) view.findViewById(R.id.MakeAppointment_category_Button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //다음 뷰 Fragment로 넘기기 --> 현재 선택한 카테고리의 유효성 검사를하고 카테고리의 정보를
                //뷰페이져의 3번 Fragment로 값을 전달해주어야 한다

                //뷰페이져 이동
                if(categoryStatus != 0 && !app_name_editText.getText().toString().equals("")){
                    makeAppointmentActivity.app_category = categoryStatus;
                    makeAppointmentActivity.app_name = app_name_editText.getText().toString();
                    makeAppointmentActivity.viewPager.setCurrentItem(1);
                }else{
                    Toast.makeText(getContext(), "카테고리와 이름을 선택해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        categoryStatus = makeAppointmentActivity.CATEGORY_NULL;

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.dropdownlayout, makeAppointmentActivity.CATEGORY );

        materialBetterSpinner = (Spinner) view.findViewById(R.id.MakeAppointment_category_BetterSpinner);
        materialBetterSpinner.setAdapter(adapter);
        materialBetterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d(MYTAG, "일단 onItemSeleted안으로 들어오긴 함");
                //입력해야함!!
                switch (parent.getItemAtPosition(position).toString()) {
                    case "카페":
                        categoryStatus = makeAppointmentActivity.CATEGORY_CAFE;
                        break;
                    case "식당":
                        categoryStatus = makeAppointmentActivity.CATEGORY_RESTAURANT;
                        break;
                    case "스터디룸":
                        categoryStatus = makeAppointmentActivity.CATEGORY_STUDYROOM;
                        break;
                    case "Category":
                        categoryStatus = makeAppointmentActivity.CATEGORY_NULL;
                        break;
                    default:

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}
