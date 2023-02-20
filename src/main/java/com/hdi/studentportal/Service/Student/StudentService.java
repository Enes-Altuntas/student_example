package com.hdi.studentportal.Service.Student;

import com.hdi.studentportal.Request.Student.SaveStudentRequest;
import com.hdi.studentportal.Request.Student.UpdateStudentRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import java.util.List;

public interface StudentService {

  List<GetStudentResponse> getAllStudents();

  GetStudentResponse saveStudent(SaveStudentRequest studentRequest);

  GetStudentResponse updateStudent(UpdateStudentRequest studentRequest);

  GetStudentResponse assignLessonToStudent(Long studentId, Long lessonId);

  GetStudentResponse removeLessonFromStudent(Long studentId, Long lessonId);

  List<GetLessonResponse> listLessonsOfStudent(Long studentId);

}
