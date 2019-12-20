package com.dongkyoo.gongzza.post;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.dongkyoo.gongzza.cache.AppDatabase;
import com.dongkyoo.gongzza.cache.CacheCallback;
import com.dongkyoo.gongzza.cache.PostDao;
import com.dongkyoo.gongzza.chat.chattingRoomList.ChattingRoomListModel;
import com.dongkyoo.gongzza.dtos.PostDto;
import com.dongkyoo.gongzza.vos.Participant;
import com.dongkyoo.gongzza.vos.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends AndroidViewModel {

    private static final String TAG = "PostViewModel";

    private MutableLiveData<PostDto> _post = new MutableLiveData<>();
    private MutableLiveData<Boolean> _isMember = new MutableLiveData<>();

    public LiveData<Boolean> isMember = _isMember;
    public LiveData<PostDto> post = _post;

    private User user;
    private PostModel postModel;

    private PostDao postDao;

    public PostViewModel(Application application, PostDto post, User user) {
        super(application);
        this.postModel = new PostModel(application);
        this.user = user;

        _post.setValue(post);
        _isMember.setValue(post.isMember(user));

        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DB_NAME).build();
        postDao = db.postDao();
    }

    void enroll() {
        postModel.enroll(post.getValue(), user, new Callback<Participant>() {
            @Override
            public void onResponse(Call<Participant> call, Response<Participant> response) {
                if (response.code() == 200) {
                    PostDto postDto = post.getValue();
                    postDto.enroll(response.body());
                    _post.setValue(postDto);
                    _isMember.setValue(true);
                } else {
                    Log.e(TAG, "Post 가입 실패");
                }
            }

            @Override
            public void onFailure(Call<Participant> call, Throwable t) {
                Log.e(TAG, "Post 가입 실패", t);
            }
        });
    }

    public void leave(CacheCallback<Boolean> callback) {
        postModel.leave(post.getValue(), user.getId(), new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == 200) {
                    if (response.body()) {
                        Log.i(TAG, "Post 탈퇴 성공");

                        PostDto postDto = post.getValue();
                        postDto.leave(user);
                        _post.setValue(postDto);
                        _isMember.setValue(false);

                        Handler handler = new Handler();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                postDao.leavePost(post.getValue());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (callback != null)
                                            callback.onReceive(true);
                                    }
                                }, 200);
                            }
                        }).start();
                    } else {
                        Log.e(TAG, "Post 탈퇴 실패");
                    }
                } else {
                    Log.e(TAG, "Post 탈퇴 실패");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e(TAG, "Post 탈퇴 실패", t);
            }
        });
    }
}
