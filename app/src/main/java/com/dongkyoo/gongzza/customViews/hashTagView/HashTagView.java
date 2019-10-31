package com.dongkyoo.gongzza.customViews.hashTagView;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.ContextCompat;

import com.dongkyoo.gongzza.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

/**
 * 작성자 : 이동규
 * 해쉬태그를 보여주는 뷰
 */
public class HashTagView extends MaterialCardView {

    private Context context;
    private AppCompatAutoCompleteTextView autoCompleteTextView;
    private TextView textView;
    private ImageView cancelImageView;

    public HashTagView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public HashTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public HashTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_hash_tag, this, true);

        setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        setRadius(context.getResources().getDimension(R.dimen.margin3));

        autoCompleteTextView = findViewById(R.id.view_hash_tag_autoCompleteTextView);
        textView = findViewById(R.id.view_hash_tag_textView);
        cancelImageView = findViewById(R.id.view_hash_tag_cancel_imageView);

        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    setEditMode(false);
                }
                return false;
            }
        });

        autoCompleteTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setEditMode(false);
                }
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    setEditMode(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancelImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(View.GONE);
            }
        });
    }

    private void setEditMode(boolean flag) {
        if (autoCompleteTextView.getText().length() == 0) {
            setVisibility(GONE);
            return;
        }

        if (flag) {
            autoCompleteTextView.setVisibility(VISIBLE);
            textView.setVisibility(GONE);
            cancelImageView.setVisibility(GONE);
        } else {
            textView.setText(autoCompleteTextView.getText());

            autoCompleteTextView.setVisibility(GONE);
            textView.setVisibility(VISIBLE);
            cancelImageView.setVisibility(VISIBLE);
        }
    }

    public void setContentList(List<String> contentList) {
        HashSearchAdapter adapter = new HashSearchAdapter(context, android.R.layout.simple_dropdown_item_1line, contentList);
        autoCompleteTextView.setAdapter(adapter);
    }
}
