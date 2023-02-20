package com.hdi.studentportal.Controller.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.hdi.studentportal.Request.Student.SaveStudentRequest;
import com.hdi.studentportal.Request.Student.UpdateStudentRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Student.StudentService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

  @InjectMocks
  private StudentController studentController;
  @Mock
  private StudentService studentService;

  @Test
  void saveStudent_shouldReturnCreatedStudent() {
    SaveStudentRequest saveStudentRequest = new SaveStudentRequest();
    GetStudentResponse createdStudent = new GetStudentResponse();

    when(studentService.saveStudent(any(SaveStudentRequest.class))).thenReturn(createdStudent);

    ResponseEntity<GetStudentResponse> responseEntity = studentController.saveStudent(
        saveStudentRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(createdStudent, responseEntity.getBody());
  }

  @Test
  void updateStudent_shouldReturnUpdatedStudent() {
    UpdateStudentRequest updateStudentRequest = new UpdateStudentRequest();
    GetStudentResponse updatedStudent = new GetStudentResponse();

    when(studentService.updateStudent(any(UpdateStudentRequest.class))).thenReturn(updatedStudent);

    ResponseEntity<GetStudentResponse> responseEntity = studentController.updateStudent(
        updateStudentRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(updatedStudent, responseEntity.getBody());
  }

  @Test
  void getAllStudents_shouldReturnAllStudents() {
    List<GetStudentResponse> allStudents = new ArrayList<>();

    when(studentService.getAllStudents()).thenReturn(allStudents);

    ResponseEntity<List<GetStudentResponse>> responseEntity = studentController.getAllStudents();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(allStudents, responseEntity.getBody());
  }

  @Test
  void assignLessonToStudent_shouldReturnUpdatedStudent() {
    Long studentId = 1L;
    Long lessonId = 2L;
    GetStudentResponse updatedStudent = new GetStudentResponse();

    when(studentService.assignLessonToStudent(studentId, lessonId)).thenReturn(updatedStudent);

    ResponseEntity<GetStudentResponse> responseEntity = studentController.assignLessonToStudent(
        studentId, lessonId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(updatedStudent, responseEntity.getBody());
  }

  @Test
  void removeLessonFromStudent_shouldReturnUpdatedStudent() {
    Long studentId = 1L;
    Long lessonId = 2L;
    GetStudentResponse updatedStudent = new GetStudentResponse();

    when(studentService.removeLessonFromStudent(studentId, lessonId)).thenReturn(updatedStudent);

    ResponseEntity<GetStudentResponse> responseEntity = studentController.removeLessonFromStudent(
        studentId, lessonId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(updatedStudent, responseEntity.getBody());
  }

  @Test
  void listLessonsOfStudent_shouldReturnLessons() {
    Long studentId = 1L;
    List<GetLessonResponse> lessons = new ArrayList<>();

    when(studentService.listLessonsOfStudent(studentId)).thenReturn(lessons);

    ResponseEntity<List<GetLessonResponse>> responseEntity = studentController.listLessonsOfStudent(
        studentId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(lessons, responseEntity.getBody());
  }
}