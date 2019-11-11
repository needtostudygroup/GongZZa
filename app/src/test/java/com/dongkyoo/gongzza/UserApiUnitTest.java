package com.dongkyoo.gongzza;

import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.User;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserApiUnitTest {

    @Test
    public void signUpTest() throws Exception {
        UserApi userApi = Networks.retrofit.create(UserApi.class);
        Call<User> call = userApi.signUp(MockData.getMockUser());
        Response<User> response = call.execute();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    /**
     * 로그인 성공
     * @throws Exception
     */
    @Test
    public void getUserByIdPwTest() throws Exception {
        UserApi userApi = Networks.retrofit.create(UserApi.class);
        Call<User> call = userApi.getUserByIdPw("testId", "testPassword");
        Response<User> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    /**
     * 로그인 실패
     * @throws Exception
     */
    @Test
    public void getUserByIdPwFailure() throws Exception {
        UserApi userApi = Networks.retrofit.create(UserApi.class);
        Call<User> call = userApi.getUserByIdPw("testId", "testPasswordasdfasdf");
        Response<User> response = call.execute();
        assertEquals(500, response.code());
    }

    @Test
    public void findId() throws Exception {

    }
}
