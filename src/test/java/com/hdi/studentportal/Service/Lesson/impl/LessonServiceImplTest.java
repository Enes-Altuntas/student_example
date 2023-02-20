package com.hdi.studentportal.Service.Lesson.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import com.hdi.studentportal.Entity.Student.Student;
import com.hdi.studentportal.Mapper.Lesson.LessonResponseMapper;
import com.hdi.studentportal.Mapper.Student.StudentResponseMapper;
import com.hdi.studentportal.Repository.Lesson.LessonRepository;
import com.hdi.studentportal.Request.Lesson.SaveLessonRequest;
import com.hdi.studentportal.Request.Lesson.UpdateLessonRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

  @Mock
  private LessonRepository lessonRepository;
  @Mock
  private LessonResponseMapper lessonResponseMapper;
  @Mock
  private StudentResponseMapper studentResponseMapper;

  @InjectMocks
  private LessonServiceImpl lessonServiceImpl;

  @Test
  void whenSaveLessonWithProperRequest_itShouldReturnProperResponse() {
    SaveLessonRequest lessonRequest = new SaveLessonRequest();
    lessonRequest.setLessonName("Physics");
    lessonRequest.setStudentQuota(3);

    Lesson savedLesson = new Lesson();
    savedLesson.setId(1L);
    savedLesson.setLessonName("Physics");
    savedLesson.setStudentQuota(3);

    GetLessonResponse expectedResponse = new GetLessonResponse();
    expectedResponse.setId(1L);
    expectedResponse.setLessonName("Physics");
    expectedResponse.setStudentQuota(3);

    when(lessonRepository.save(Mockito.any(Lesson.class))).thenReturn(savedLesson);
    when(lessonResponseMapper.fromDTOToResponse(savedLesson)).thenReturn(expectedResponse);

    GetLessonResponse actualResponse = lessonServiceImpl.saveLesson(lessonRequest);

    assertEquals(expectedResponse, actualResponse);
    verify(lessonRepository).save(Mockito.any(Lesson.class));
    verify(lessonResponseMapper).fromDTOToResponse(savedLesson);
  }

  @Test
  void whenUpdateLessonWithProperRequest_itShouldReturnProperResponse() {
    UpdateLessonRequest lessonRequest = new UpdateLessonRequest();
    lessonRequest.setId(1L);
    lessonRequest.setLessonName("Physics");
    lessonRequest.setStudentQuota(3);

    Lesson currentLesson = new Lesson();
    currentLesson.setId(1L);
    currentLesson.setLessonName("Math");
    currentLesson.setStudentQuota(5);

    Lesson savedLesson = new Lesson();
    savedLesson.setId(1L);
    savedLesson.setLessonName("Physics");
    savedLesson.setStudentQuota(3);

    GetLessonResponse expectedResponse = new GetLessonResponse();
    expectedResponse.setId(1L);
    expectedResponse.setLessonName("Physics");
    expectedResponse.setStudentQuota(3);

    when(lessonRepository.findById(lessonRequest.getId()))
        .thenReturn(java.util.Optional.of(currentLesson));
    when(lessonRepository.save(Mockito.any(Lesson.class))).thenReturn(savedLesson);
    when(lessonResponseMapper.fromDTOToResponse(savedLesson)).thenReturn(expectedResponse);

    GetLessonResponse actualResponse = lessonServiceImpl.updateLesson(lessonRequest);

    assertEquals(expectedResponse, actualResponse);
    verify(lessonRepository).findById(lessonRequest.getId());
    verify(lessonRepository).save(currentLesson);
    verify(lessonResponseMapper).fromDTOToResponse(savedLesson);
  }

  @Test
  void whenUpdateLessonWithInproperRequest_itShouldReturnNotFound() {
    Long lessonId = 1L;

    UpdateLessonRequest lessonRequest = new UpdateLessonRequest();
    lessonRequest.setId(lessonId);
    lessonRequest.setLessonName("Fizik");
    lessonRequest.setStudentQuota(3);

    when(lessonRepository.findById(lessonId)).thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class, () -> {
      lessonServiceImpl.updateLesson(lessonRequest);
    });
  }

  @Test
  void whenGetStudentsOfLesson_itShouldReturnListOfStudents() {
    Lesson lesson = new Lesson();
    lesson.setId(1L);
    lesson.setLessonName("Physics");
    lesson.setStudentQuota(3);

    Student student1 = new Student();
    student1.setId(1L);
    student1.setName("Ali");
    student1.setSurname("Veli");

    Student student2 = new Student();
    student2.setId(2L);
    student2.setName("Ahmet");
    student2.setSurname("Mehmet");

    lesson.getStudentList().add(student1);
    lesson.getStudentList().add(student2);

    when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
    when(
        studentResponseMapper.fromDTOListToResponseList(new ArrayList<>(lesson.getStudentList())))
        .thenReturn(Arrays.asList(
            new GetStudentResponse(1L, "Ali", "Veli"),
            new GetStudentResponse(2L, "Ahmet", "Mehmet")
        ));

    List<GetStudentResponse> response = lessonServiceImpl.getStudentsOfLesson(1L);

    assertEquals(2, response.size());
    assertEquals(1L, response.get(0).getId());
    assertEquals("Ali", response.get(0).getName());
    assertEquals("Veli", response.get(0).getSurname());
    assertEquals(2L, response.get(1).getId());
    assertEquals("Ahmet", response.get(1).getName());
    assertEquals("Mehmet", response.get(1).getSurname());
  }

  @Test
  void getAllLessons() {
    List<Lesson> lessonList = new ArrayList<>();

    Lesson lesson1 = new Lesson();
    lesson1.setId(1L);
    lesson1.setLessonName("Physics");
    lesson1.setStudentQuota(20);

    Lesson lesson2 = new Lesson();
    lesson2.setId(2L);
    lesson2.setLessonName("Chemistry");
    lesson2.setStudentQuota(30);

    lessonList.add(lesson1);
    lessonList.add(lesson2);

    when(lessonRepository.findAll()).thenReturn(lessonList);

    List<GetLessonResponse> getLessonResponses = new ArrayList<>();

    GetLessonResponse lessonResponse1 = new GetLessonResponse();
    lessonResponse1.setId(1L);
    lessonResponse1.setLessonName("Physics");
    lessonResponse1.setStudentQuota(20);

    GetLessonResponse lessonResponse2 = new GetLessonResponse();
    lessonResponse2.setId(2L);
    lessonResponse2.setLessonName("Chemistry");
    lessonResponse2.setStudentQuota(30);

    getLessonResponses.add(lessonResponse1);
    getLessonResponses.add(lessonResponse2);

    when(lessonResponseMapper.fromDTOListToResponseList(lessonList))
        .thenReturn(getLessonResponses);

    List<GetLessonResponse> result = lessonServiceImpl.getAllLessons();

    assertEquals(2, result.size());
    assertEquals(lessonResponse1, result.get(0));
    assertEquals(lessonResponse2, result.get(1));
  }
}