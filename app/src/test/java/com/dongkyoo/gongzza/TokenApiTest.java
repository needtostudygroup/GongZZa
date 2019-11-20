package com.dongkyoo.gongzza;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.TokenApi;
import com.dongkyoo.gongzza.vos.Token;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class TokenApiTest {

    @Test
    public void registerToken() throws Exception {
        Networks.createRetrofit(InstrumentationRegistry.getInstrumentation().getTargetContext());
        TokenApi tokenApi = Networks.getRetrofit().create(TokenApi.class);
        Call<Token> call = tokenApi.registerToken(MockData.getMockToken());
        Response<Token> response = call.execute();
        assertEquals(200, response.code());
        assertNotNull(response.body());
    }

}
