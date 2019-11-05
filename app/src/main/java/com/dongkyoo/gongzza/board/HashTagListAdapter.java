package com.dongkyoo.gongzza.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.databinding.ItemHashtagListBinding;
import com.dongkyoo.gongzza.vos.HashTag;

import java.util.ArrayList;
import java.util.List;

public class HashTagListAdapter extends RecyclerView.Adapter<HashTagListAdapter.ViewHolder> {

    private Context context;
    private List<HashTag> hashTagList;

    public HashTagListAdapter(Context context) {
        this.context = context;
    }

    void setHashTagList(List<HashTag> hashTagList) {
        this.hashTagList = hashTagList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(layoutInflater.inflate(R.layout.item_hashtag_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setHashTag(hashTagList.get(position));
    }

    @Override
    public int getItemCount() {
        return hashTagList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
        }

        void setHashTag(HashTag hashTag) {
            ItemHashtagListBinding binding = DataBindingUtil.bind(itemView);
            binding.setHashTag(hashTag);
        }
    }
}
