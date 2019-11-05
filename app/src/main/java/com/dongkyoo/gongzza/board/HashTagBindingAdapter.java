package com.dongkyoo.gongzza.board;

import androidx.databinding.BindingAdapter;

import com.dongkyoo.gongzza.customViews.hashTagView.HashTagView;
import com.dongkyoo.gongzza.vos.HashTag;

public class HashTagBindingAdapter {

    @BindingAdapter("hashTag")
    public static void hashTag(HashTagView hashTagView, HashTag tag) {
        hashTagView.setText(tag.getContent());
        hashTagView.setColor(tag.getColor());
        hashTagView.setEditMode(false);
    }
}
