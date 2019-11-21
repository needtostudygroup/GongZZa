package com.dongkyoo.gongzza.chat.chattingRoomList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.chat.chattingRoom.ChatActivity;
import com.dongkyoo.gongzza.dtos.PostChatDto;

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
        ChattingRoomListViewModel viewModel = new ChattingRoomListViewModel();
        viewModel.postChat.observe(this, new Observer<PostChatDto>() {
            @Override
            public void onChanged(PostChatDto postChatDto) {

            }
        });

        return view;
    }

    private void initView(View view) {
        RelativeLayout chattingList = view.findViewById(R.id.chatting_room_layout);
        chattingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
