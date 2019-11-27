package com.dongkyoo.gongzza.course;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dongkyoo.gongzza.dtos.CourseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {

    private MutableLiveData<CourseState> _state = new MutableLiveData<>();
    private MutableLiveData<List<CourseDto>> _courseList = new MutableLiveData<>();

    public LiveData<CourseState> state = _state;
    public LiveData<List<CourseDto>> courseList = _courseList;

    private CourseModel courseModel;


    public CourseViewModel() {
        _state.setValue(new CourseState());
        courseModel = new CourseModel();
    }

    void createCourse(CourseDto courseDto) {
        courseModel.createCourse(courseDto, new Callback<CourseDto>() {
            @Override
            public void onResponse(Call<CourseDto> call, Response<CourseDto> response) {
                if (response.isSuccessful()) {
                    _state.setValue(new CourseState(200));
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

    void loadCourseList(String userId) {
        courseModel.loadAllCourseList(userId, new Callback<List<CourseDto>>() {
            @Override
            public void onResponse(Call<List<CourseDto>> call, Response<List<CourseDto>> response) {
                if (response.isSuccessful()) {
                    _courseList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CourseDto>> call, Throwable t) {

            }
        });
    }

    public static class CourseState {
        public int state;
        public String errorMessage;

        public CourseState() {
        }

        public CourseState(int state) {
            this.state = state;
            this.errorMessage = null;
        }

        public CourseState(int state, String errorMessage) {
            this.state = state;
            this.errorMessage = errorMessage;
        }
    }
}
