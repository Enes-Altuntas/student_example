package com.hdi.studentportal.Mapper.Student;

import com.hdi.studentportal.Entity.Student.Student;
import com.hdi.studentportal.Mapper.BaseResponseMapper;
import com.hdi.studentportal.Mapper.Lesson.LessonResponseMapper;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LessonResponseMapper.class)
public interface StudentResponseMapper extends BaseResponseMapper<GetStudentResponse, Student> {

}
