package com.dongkyoo.gongzza.chat.chattingRoom;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dongkyoo.gongzza.cache.CacheCallback;
import com.dongkyoo.gongzza.dtos.PostChatDto;
import com.dongkyoo.gongzza.dtos.SendingChatDto;
import com.dongkyoo.gongzza.post.PostModel;
import com.dongkyoo.gongzza.vos.ChatLog;
import com.dongkyoo.gongzza.vos.Post;
import com.dongkyoo.gongzza.vos.User;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {

    private MutableLiveData<ChatState> _chatState = new MutableLiveData<>();

    public LiveData<ChatState> chatState = _chatState;

    private ChatModel chatModel;
    private PostModel postModel;
    private User me;
    private PostChatDto postChatDto;

    public ChatViewModel(Context context, PostChatDto postChatDto, User me) {
        this.me = me;
        this.postChatDto = postChatDto;

        chatModel = new ChatModel(context);
    }

    public ChatViewModel(Context context, int postId, User me) {
        this.me = me;
        chatModel = new ChatModel(context);
        postModel = new PostModel(context);

        postModel.selectPostById(postId, new CacheCallback<Post>() {
            @Override
            public void onReceive(Post post) {
                postChatDto = new PostChatDto(post);
                chatModel.loadRecentChatBeforeDatetime(
                        postChatDto.getId(),
                        new Date(),
                        0,
                        15, new CacheCallback<List<ChatLog>>() {
                            @Override
                            public void onReceive(List<ChatLog> chatLogList) {
                                if (chatLogList != null)
                                    postChatDto.getChatLogList().addAll(chatLogList);
                            }
                        });
            }
        });
    }

    public void sendChat(String content) {
        if (postChatDto == null)
            return;

        ChatLog chatLog = new ChatLog(0, postChatDto.getId(), me.getId(), content, new Date(System.currentTimeMillis()));
        SendingChatDto sendingChatDto = new SendingChatDto(chatLog);

        postChatDto.getChatLogList().add(sendingChatDto);
        _chatState.setValue(new ChatState(ChatState.CREATE));

        chatModel.sendChat(chatLog, new Callback<ChatLog>() {
            @Override
            public void onResponse(Call<ChatLog> call, Response<ChatLog> response) {
                if (response.isSuccessful()) {
                    int index = postChatDto.getChatLogList().indexOf(sendingChatDto);
                    postChatDto.getChatLogList().set(index, chatLog);
                    _chatState.setValue(new ChatState(ChatState.MODIFY));
                } else {
                    int index = postChatDto.getChatLogList().indexOf(sendingChatDto);
                    sendingChatDto.setErrorMessage("실패");
                    postChatDto.getChatLogList().set(index, sendingChatDto);
                    _chatState.setValue(new ChatState(ChatState.MODIFY, index));
                }
            }

            @Override
            public void onFailure(Call<ChatLog> call, Throwable t) {
                int index = postChatDto.getChatLogList().indexOf(sendingChatDto);
                sendingChatDto.setErrorMessage("실패");
                postChatDto.getChatLogList().set(index, sendingChatDto);
                _chatState.setValue(new ChatState(ChatState.MODIFY, index));
            }
        });
    }
}
