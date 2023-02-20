package com.hdi.studentportal.Service.Lesson;

import com.hdi.studentportal.Request.Lesson.SaveLessonRequest;
import com.hdi.studentportal.Request.Lesson.UpdateLessonRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import java.util.List;

public interface LessonService {

  GetLessonResponse saveLesson(SaveLessonRequest lessonRequest);

  GetLessonResponse updateLesson(UpdateLessonRequest lessonRequest);

  List<GetStudentResponse> getStudentsOfLesson(Long lessonId);

  List<GetLessonResponse> getAllLessons();

}
