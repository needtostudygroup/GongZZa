package com.dongkyoo.gongzza.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.dongkyoo.gongzza.dtos.PostDto;
import com.dongkyoo.gongzza.dtos.SendingChatDto;
import com.dongkyoo.gongzza.vos.ChatLog;
import com.dongkyoo.gongzza.vos.Post;

import java.text.SimpleDateFormat;

public class ChatBindingAdapter {

    @BindingAdapter("chat_sentAt")
    public static void sentAt(TextView textView, ChatLog chatLog) {
        if (chatLog == null)
            return;

        SimpleDateFormat format = new SimpleDateFormat("MM월 dd일 HH시 mm분");
        textView.setText(format.format(chatLog.getSentAt()));
    }

    @BindingAdapter("chat_isError")
    public static void isError(ViewGroup viewGroup, ChatLog chatLog) {
        if (chatLog instanceof SendingChatDto) {
            SendingChatDto c = (SendingChatDto) chatLog;
            if (c.getErrorMessage() != null)
                viewGroup.setVisibility(View.VISIBLE);
            else
                viewGroup.setVisibility(View.GONE);
            return;
        }
        viewGroup.setVisibility(View.GONE);
    }
}
