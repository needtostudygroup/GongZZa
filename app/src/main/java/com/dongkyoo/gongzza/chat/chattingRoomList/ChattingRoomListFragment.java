package com.dongkyoo.gongzza.chat.chattingRoomList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.chat.chattingRoom.ChatActivity;
import com.dongkyoo.gongzza.dtos.PostChatDto;
import com.dongkyoo.gongzza.dtos.PostChatDtos;
import com.dongkyoo.gongzza.vos.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 작성자: 이동규
 * 채팅방 리스트를 보여주는 프래그먼트
 */
public class ChattingRoomListFragment extends Fragment {

    private static final String TAG = "ChattingRoomListFrag";
    private User me;
    private ChattingRoomListAdapter adapter;

    private ChattingRoomListFragment() {
        // Required empty public constructor
    }

    public static ChattingRoomListFragment newInstance(User me) {
        ChattingRoomListFragment chattingRoomListFragment = new ChattingRoomListFragment();
        chattingRoomListFragment.me = me;
        return chattingRoomListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting_room_list, container, false);

        initView(view);

        ChattingRoomListViewModel viewModel = new ChattingRoomListViewModel(getActivity().getApplication(), me);
        viewModel.loadRemoteChatLog(viewModel.getLastChatReceivedDatetime());

        viewModel.postChatList.observe(this, new Observer<List<PostChatDto>>() {
            @Override
            public void onChanged(List<PostChatDto> newPostChatDtos) {
                Log.i(TAG, "채팅데이터 로딩");
                adapter.setPostChatDtoList(newPostChatDtos);
            }
        });
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.chatting_room_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChattingRoomListAdapter(getActivity(), new ArrayList<>(), me);
        recyclerView.setAdapter(adapter);
    }
}
