package com.dongkyoo.gongzza.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.post.board.BoardFragment;
import com.dongkyoo.gongzza.chat.chattingRoomList.ChattingRoomListFragment;
import com.dongkyoo.gongzza.home.HomeFragment;
import com.dongkyoo.gongzza.all.AllFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private MainTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면 전환 프래그먼트 선언 및 초기 화면 설정

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        adapter = new MainTabAdapter(getSupportFragmentManager(),
                new Fragment[] {
                        new HomeFragment(),
                        new BoardFragment(),
                        new ChattingRoomListFragment(),
                        new AllFragment()
                },
                getResources().getStringArray(R.array.tab_name_list)
        );

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
}
