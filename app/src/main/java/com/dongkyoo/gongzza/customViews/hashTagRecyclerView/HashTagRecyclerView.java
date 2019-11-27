package com.dongkyoo.gongzza.customViews.hashTagRecyclerView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dongkyoo.gongzza.vos.HashTag;

import java.util.List;

public class HashTagRecyclerView extends RecyclerView {

    private Context context;
    private List<HashTag> hashTagList;
    private HashTagRecyclerAdapter adapter;

    public HashTagRecyclerView(@NonNull Context context) {
        super(context);

        this.context = context;
        init();
    }

    public HashTagRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public HashTagRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init();
    }

    private void init() {
        adapter = new HashTagRecyclerAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context, HORIZONTAL, false);
        setLayoutManager(manager);
        setAdapter(adapter);
    }

    public void setHashTagList(List<HashTag> hashTagList) {
        this.hashTagList = hashTagList;

        adapter.setHashTagList(hashTagList);
    }

    public void setAppendable(boolean flag) {
        adapter.setAppendable(flag);
    }
}
