package com.dongkyoo.gongzza.chattingRoomList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.chattingRoom.ChatActivity;

/**
 * 작성자: 이동규
 * 채팅방 리스트를 보여주는 프래그먼트
 */
public class ChattingRoomListFragment extends Fragment {

    public ChattingRoomListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting_room_list, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        RelativeLayout chattingList = view.findViewById(R.id.chattingRoom);
        chattingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

}
