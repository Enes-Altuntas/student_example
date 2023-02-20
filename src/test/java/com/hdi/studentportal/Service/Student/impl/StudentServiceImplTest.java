package com.hdi.studentportal.Service.Student.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import com.hdi.studentportal.Entity.Student.Student;
import com.hdi.studentportal.Mapper.Lesson.LessonResponseMapper;
import com.hdi.studentportal.Mapper.Student.StudentResponseMapper;
import com.hdi.studentportal.Repository.Lesson.LessonRepository;
import com.hdi.studentportal.Repository.Student.StudentRepository;
import com.hdi.studentportal.Request.Student.SaveStudentRequest;
import com.hdi.studentportal.Request.Student.UpdateStudentRequest;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

  @Mock
  private StudentRepository studentRepository;
  @Mock
  private LessonRepository lessonRepository;
  @Mock
  private StudentResponseMapper studentResponseMapper;
  @Mock
  private LessonResponseMapper lessonResponseMapper;
  @InjectMocks
  private StudentServiceImpl studentService;

  @Test
  void testGetAllStudents() {
    List<Student> studentList = new ArrayList<>();
    Student student = new Student();
    student.setName("John");
    student.setSurname("Doe");
    studentList.add(student);

    List<GetStudentResponse> responseList = new ArrayList<>();
    GetStudentResponse response = new GetStudentResponse();
    response.setName("John");
    response.setSurname("Doe");
    responseList.add(response);

    when(studentRepository.findAll()).thenReturn(studentList);
    when(studentResponseMapper.fromDTOListToResponseList(studentList)).thenReturn(responseList);

    List<GetStudentResponse> result = studentService.getAllStudents();

    assertEquals(responseList, result);
  }

  @Test
  void testSaveStudent() {
    SaveStudentRequest request = new SaveStudentRequest();
    request.setName("John");
    request.setSurname("Doe");

    Student student = new Student();
    student.setName("John");
    student.setSurname("Doe");

    GetStudentResponse response = new GetStudentResponse();
    response.setName("John");
    response.setSurname("Doe");

    when(studentRepository.saveAndFlush(student)).thenReturn(student);
    when(studentResponseMapper.fromDTOToResponse(student)).thenReturn(response);

    GetStudentResponse result = studentService.saveStudent(request);

    assertEquals(response, result);
  }

  @Test
  void testUpdateStudent() {
    UpdateStudentRequest request = new UpdateStudentRequest();
    request.setId(1L);
    request.setName("John");
    request.setSurname("Doe");

    Student student = new Student();
    student.setId(1L);
    student.setName("Jane");
    student.setSurname("Doe");

    Student updatedStudent = new Student();
    updatedStudent.setId(1L);
    updatedStudent.setName("John");
    updatedStudent.setSurname("Doe");

    GetStudentResponse response = new GetStudentResponse();
    response.setId(1L);
    response.setName("John");
    response.setSurname("Doe");

    when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
    when(studentRepository.saveAndFlush(updatedStudent)).thenReturn(updatedStudent);
    when(studentResponseMapper.fromDTOToResponse(updatedStudent)).thenReturn(response);

    GetStudentResponse result = studentService.updateStudent(request);

    assertEquals(response, result);
  }

  @Test
  public void testAssignLessonToStudentConcurrently() throws InterruptedException {
    final Long studentId = 1L;
    final Long lessonId = 2L;

    Student student = new Student();
    student.setId(studentId);

    Lesson lesson = new Lesson();
    lesson.setId(lessonId);
    lesson.setStudentQuota(2);
    lesson.setStudentList(new HashSet<>());

    GetStudentResponse studentResponse = new GetStudentResponse();
    studentResponse.setId(studentId);

    when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
    when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
    when(studentResponseMapper.fromDTOToResponse(student)).thenReturn(studentResponse);

    Runnable task = () -> {
      studentService.assignLessonToStudent(studentId, lessonId);
    };

    ExecutorService executor = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executor.execute(task);
    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);

    verify(lessonRepository, times(10)).saveAndFlush(lesson);
  }

  @Test
  public void testRemoveLessonFromStudentConcurrently() throws InterruptedException {
    final Long studentId = 1L;
    final Long lessonId = 2L;

    Student student = new Student();
    student.setId(studentId);

    Lesson lesson = new Lesson();
    lesson.setId(lessonId);
    lesson.setStudentQuota(2);
    lesson.setStudentList(new HashSet<>());
    lesson.getStudentList().add(student);

    GetStudentResponse studentResponse = new GetStudentResponse();
    studentResponse.setId(studentId);

    when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
    when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
    when(studentResponseMapper.fromDTOToResponse(student)).thenReturn(studentResponse);

    Runnable task = () -> {
      studentService.removeLessonFromStudent(studentId, lessonId);
    };

    ExecutorService executor = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executor.execute(task);
    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);

    verify(lessonRepository, times(10)).saveAndFlush(lesson);
  }
}