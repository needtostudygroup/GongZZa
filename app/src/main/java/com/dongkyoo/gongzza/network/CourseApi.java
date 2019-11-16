package com.dongkyoo.gongzza.network;

import com.dongkyoo.gongzza.dtos.CourseDto;
import com.dongkyoo.gongzza.vos.AuthMail;
import com.dongkyoo.gongzza.vos.Course;
import com.dongkyoo.gongzza.vos.User;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CourseApi {

    @POST("/courses")
    Call<CourseDto> insertCourse(@Body CourseDto courseDto);

    @PUT("/courses/{id}")
    Call<Boolean> updateCourse(@Path("id") int courseId, @Body Course course);

    @DELETE("/courses/{id}")
    Call<Boolean> deleteCourse(@Path("id") int courseId);

    @GET("/courses")
    Call<List<CourseDto>> selectCourseDtoListByUserId(@Query("userId") String userId);

}
