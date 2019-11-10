package com.dongkyoo.gongzza;

import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.User;

import org.junit.Test;
import static org.junit.Assert.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserApiUnitTest {

    @Test
    public void signUpTest() throws Exception {
        UserApi userApi = Networks.retrofit.create(UserApi.class);
        Call<User> call = userApi.signUp(MockData.getMockUser());
        Response<User> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

    @Test
    public void getUserByIdPwTest() throws Exception {
        UserApi userApi = Networks.retrofit.create(UserApi.class);
        Call<User> call = userApi.getUserByIdPw("testId", "testPassword");
        Response<User> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }
}
