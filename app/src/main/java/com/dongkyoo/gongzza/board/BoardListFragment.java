package com.dongkyoo.gongzza.board;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dongkyoo.gongzza.R;
import com.google.android.material.card.MaterialCardView;

/**
 * 작성자: 이동규
 * 게시판 목록을 보여주는 프래그먼트
 */
public class BoardListFragment extends Fragment {

    public BoardListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);
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

        MaterialCardView cardView = view.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 4;
                int max = 4;

                if (count == max) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("주의")
                            .setMessage("인원이 다 찼습니다")
                            .setPositiveButton("확인", null)
                            .show();
                }
            }
        });
    }

}
