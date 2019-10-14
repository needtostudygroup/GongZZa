package com.dongkyoo.gongzza.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.alarm.AlarmFragment;
import com.dongkyoo.gongzza.alarm.AlarmPreferenceFragment;
import com.dongkyoo.gongzza.breaktime.BreakTimeFragment;
import com.dongkyoo.gongzza.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private MainTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        adapter = new MainTabAdapter(getSupportFragmentManager(),
                new Fragment[] {
                        new HomeFragment(),
                        new AlarmFragment(),
                        new BreakTimeFragment()
                },
                new String[] {
                        "홈",
                        "알람",
                        "공강"
                });

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
