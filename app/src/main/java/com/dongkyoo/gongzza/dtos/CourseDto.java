package com.dongkyoo.gongzza.dtos;

import com.dongkyoo.gongzza.vos.Course;
import com.dongkyoo.gongzza.vos.CourseInfo;

import java.util.List;
import java.util.Objects;

public class CourseDto extends Course {

    private List<CourseInfo> courseInfoList;

    public CourseDto() {
    }

    public CourseDto(int id, String userId, String name, String professor, List<CourseInfo> courseInfoList) {
        super(id, userId, name, professor);
        this.courseInfoList = courseInfoList;
    }

    public List<CourseInfo> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "courseInfoList=" + courseInfoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseDto)) return false;
        if (!super.equals(o)) return false;
        CourseDto courseDto = (CourseDto) o;
        return Objects.equals(courseInfoList, courseDto.courseInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseInfoList);
    }
}
