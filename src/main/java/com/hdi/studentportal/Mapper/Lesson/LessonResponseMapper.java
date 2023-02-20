package com.hdi.studentportal.Mapper.Lesson;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import com.hdi.studentportal.Mapper.BaseResponseMapper;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonResponseMapper extends BaseResponseMapper<GetLessonResponse, Lesson> {

}
