package com.dongkyoo.gongzza.main;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.alarm.AlarmFragment;
import com.dongkyoo.gongzza.board.BoardListFragment;
import com.dongkyoo.gongzza.chattingRoomList.ChattingRoomListFragment;
import com.dongkyoo.gongzza.home.HomeFragment;
import com.dongkyoo.gongzza.all.AllFragment;
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
                        new BoardListFragment(),
                        new ChattingRoomListFragment(),
                        new AllFragment()
                },
                getResources().getStringArray(R.array.tab_name_list)
        );

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
