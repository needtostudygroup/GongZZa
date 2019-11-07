package com.dongkyoo.gongzza.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dongkyoo.gongzza.R;

/**
 * 작성자 : 이동규
 * 전체 프래그먼트
 * "전체"라 함은 알람, 공강 겹치기, 설정 같은 기타 기능들을 모아둔 것을 말합니다.
 */
public class AllFragment extends Fragment {

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        return view;
    }
}
