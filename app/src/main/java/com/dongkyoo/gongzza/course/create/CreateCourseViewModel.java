package com.dongkyoo.gongzza.course.create;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dongkyoo.gongzza.course.CourseState;
import com.dongkyoo.gongzza.dtos.CourseDto;
import com.dongkyoo.gongzza.vos.CourseInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourseViewModel extends ViewModel {

    private MutableLiveData<CourseState> _state = new MutableLiveData<>();

    public LiveData<CourseState> state = _state;

    private CreateCourseModel createCourseModel;


    public CreateCourseViewModel() {
        _state.setValue(new CourseState());
        createCourseModel = new CreateCourseModel();
    }

    void createCourse(CourseDto courseDto) {
        if (!checkValidation(courseDto))
            return;

        createCourseModel.createCourse(courseDto, new Callback<CourseDto>() {
            @Override
            public void onResponse(Call<CourseDto> call, Response<CourseDto> response) {
                if (response.isSuccessful()) {
                    _state.setValue(new CourseState(200, response.body()));
                } else {
                    _state.setValue(new CourseState(0, "수업 생성 실패"));
                }
            }

            @Override
            public void onFailure(Call<CourseDto> call, Throwable t) {
                _state.setValue(new CourseState(0, "수업 생성 실패"));
            }
        });
    }

    boolean checkValidation(CourseDto courseDto) {
        if (TextUtils.isEmpty(courseDto.getName())) {
            _state.setValue(new CourseState(0, "수업 이름을 입력하세요"));
            return false;
        }

        if (TextUtils.isEmpty(courseDto.getProfessor())) {
            _state.setValue(new CourseState(0, "교수님 이름을 입력하세요"));
            return false;
        }

        if (courseDto.getCourseInfoList() == null || courseDto.getCourseInfoList().size() == 0) {
            _state.setValue(new CourseState(0, "수업시간은 최소 하나 있어야합니다"));
            return false;
        }

        for (CourseInfo info : courseDto.getCourseInfoList()) {
            if (!checkValidation(info))
                return false;
        }

        return true;
    }

    boolean checkValidation(CourseInfo info) {
        if (info.getStartTime() == null) {
            _state.setValue(new CourseState(0, "수업 시작 시간을 입력하세요"));
            return false;
        }

        if (info.getEndTime() == null) {
            _state.setValue(new CourseState(0, "수업 종료 시간을 입력하세요"));
            return false;
        }

        if (info.getStartTime().getTime() >= info.getEndTime().getTime()) {
            _state.setValue(new CourseState(0, "시간을 확인하세요"));
            return false;
        }

        return true;
    }
}
