package com.dongkyoo.gongzza.post;

import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.ParticipantApi;
import com.dongkyoo.gongzza.vos.Participant;
import com.dongkyoo.gongzza.vos.User;

import retrofit2.Call;
import retrofit2.Callback;

public class PostModel {

    private ParticipantApi participantApi;

    public PostModel() {
        participantApi = Networks.retrofit.create(ParticipantApi.class);
    }

    void enroll(int postId, User user, Callback<Participant> callback) {
        Call<Participant> call = participantApi.insertParticipant(new Participant(postId, user));
        call.enqueue(callback);
    }

    void leave(int postId, String userId, Callback<Boolean> callback) {
        Call<Boolean> call = participantApi.deleteParticipant(postId, userId);
        call.enqueue(callback);
    }
}
