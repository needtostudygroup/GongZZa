package com.dongkyoo.gongzza.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.dtos.PostDto;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.PostApi;
import com.dongkyoo.gongzza.post.PostActivity;
import com.dongkyoo.gongzza.post.write.WritePostActivity;
import com.dongkyoo.gongzza.vos.Config;
import com.dongkyoo.gongzza.vos.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 작성자: 이동규
 * 게시판 목록을 보여주는 프래그먼트
 */
public class BoardFragment extends Fragment implements BoardRecyclerAdapter.OnClickPost {

    private static final String TAG = "BoardFragment";
    private User me;
    private BoardRecyclerAdapter adapter = new BoardRecyclerAdapter(
            Arrays.asList(),
            this
    );

    private BoardFragment() {
        // Required empty public constructor
    }

    public static BoardFragment newInstance(User me) {
        BoardFragment boardFragment = new BoardFragment();
        boardFragment.me = me;
        return boardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        loadPost();

        FloatingActionButton writePostButton = view.findViewById(R.id.write_post_button);
        writePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WritePostActivity.class);
                intent.putExtra(Config.USER, me);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(PostDto postDto) {
        Intent intent = new Intent(getActivity(), PostActivity.class);
        intent.putExtra(Config.USER, me);
        intent.putExtra(Config.POST, postDto);
        startActivity(intent);
    }

    private void loadPost() {
        PostApi postApi = Networks.retrofit.create(PostApi.class);
        Call<List<PostDto>> call = postApi.selectRecentPostDtoList(me.getSchoolId(), me.getId(), Config.POST_LIMIT);
        call.enqueue(new Callback<List<PostDto>>() {
            @Override
            public void onResponse(Call<List<PostDto>> call, Response<List<PostDto>> response) {
                if (response.code() == 200) {
                    Log.i(TAG, "Post 불러오기 성공");
                    adapter.setPostList(response.body());
                } else {
                    Log.e(TAG, "Post 불러오기 실패");
                }
            }

            @Override
            public void onFailure(Call<List<PostDto>> call, Throwable t) {
                Log.e(TAG, "Post 불러오기 실패", t);
            }
        });
    }
}
