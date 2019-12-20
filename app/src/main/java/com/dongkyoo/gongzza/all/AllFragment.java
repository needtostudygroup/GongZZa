package com.dongkyoo.gongzza.all;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.all.Alarm.AlarmActivity;
import com.dongkyoo.gongzza.all.BreakTime.BreakTimeActivity;
import com.dongkyoo.gongzza.vos.Config;
import com.dongkyoo.gongzza.vos.User;

/**
 * 작성자 : 이동규
 * 전체 프래그먼트
 * "전체"라 함은 알람, 공강 겹치기, 설정 같은 기타 기능들을 모아둔 것을 말합니다.
 */
public class AllFragment extends Fragment {

    private User me;

    private AllFragment() {
        // Required empty public constructor
    }

    public static AllFragment newInstance(User me) {
        AllFragment fragment = new AllFragment();
        fragment.me = me;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        Button button1 = (Button)view.findViewById(R.id.alarm_button);
        Button button2 = (Button)view.findViewById(R.id.combine_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlarmActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BreakTimeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Config.USER, me);
                v.getContext().startActivity(intent);
            }
        });

        return view;
    }

  //  public void onClick( View v ) {
       // Intent intent = new Intent();
      //  ComponentName componentName = new ComponentName("com.dongkyoo.gongzza.all.BreakTime",
    //            "com.dongkyoo.gongzza.all.Alarm.AlarmFragment" );
  //      intent.setComponent( componentName );
//        startActivity( intent );
//    }
}
